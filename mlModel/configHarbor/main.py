from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List, Dict, Any
import uvicorn
import pandas as pd
import joblib

app = FastAPI()

model = joblib.load('model.pkl')
cpu_path = "cpu_v1.csv"
mb_path = "mb_v1.csv"
gpu_path = "gpu_v1.csv"
ram_path = "ram_v2.csv"
ps_path = "ps_v1.csv"

gpu_df = pd.read_csv(gpu_path)
cpu_df = pd.read_csv(cpu_path)
mb_df = pd.read_csv(mb_path)
ram_df = pd.read_csv(ram_path)
ps_df = pd.read_csv(ps_path)

types = {'Gaming': 15, 'Professional': 20, 'Office': 20, 'Multimedia': 30, 'All-in-One': 25}


class PCRequest(BaseModel):
    pcType: str
    pcPrice: float
    pcUpgrade: bool


class PCConfiguration(BaseModel):
    cpu: str
    motherboard: str
    gpu: str
    ram: str
    psu: str
    pcType: str
    pcPrice: float


def generate_random_combination(pc_type, max_price):
    cpu, gpu, ram, psu, mb, combination = None, None, None, None, None, None
    price = 0
    while True:
        mb = mb_df.sample()
        cpu = cpu_df.sample()
        gpu = gpu_df.sample()
        ram = ram_df.sample()
        psu = ps_df.sample()
        price = cpu['price'].values[0] + mb['price'].values[0] + gpu['price'].values[0] + ram['price'].values[0] + \
                psu['price'].values[0]

        combination = pd.DataFrame([{
            'cpu': cpu['cpuName'].values[0],
            'motherboard': mb['name'].values[0],
            'gpu': gpu['gpuName'].values[0],
            'ram': ram['name'].values[0],
            'psu': psu['name'].values[0],
            'type': pc_type,
            'price': price
        }])

        rating = model.predict(combination)[0]
        rating_threshold = types.get(pc_type)

        Acpu = cpu['cpuName'].values[0]
        Agpu = gpu['gpuName'].values[0]
        Amb = mb['name'].values[0]
        Aram = ram['name'].values[0]
        Apsu = psu['name'].values[0]

        if check_compatibility(cpu, mb, gpu, psu, ram) and max_price >= price and rating >= rating_threshold-5:
            break

    return combination


def check_compatibility(cpu, motherboard, gpu, psu, ram):
    if cpu['socket'].values[0] != motherboard['socket'].values[0]:
        return False
    if cpu['TDP'].values[0] + gpu['TDP'].values[0] > psu['wattage'].values[0]:
        return False
    if ram['planks'].values[0] > motherboard['memory_slots'].values[0]:
        return False
    return True


@app.post("/config", response_model=PCConfiguration)
def get_recommendation(request: PCRequest):
    try:
        if request.pcPrice < 500:
            request.pcPrice = 500

        if request.pcType == '':
            request.pcType = 'Gaming'

        config = generate_random_combination(request.pcType, request.pcPrice)
        return PCConfiguration(
            cpu=config['cpu'].values[0],
            motherboard=config['motherboard'].values[0],
            gpu=config['gpu'].values[0],
            ram=config['ram'].values[0],
            psu=config['psu'].values[0],
            pcType=config['type'].values[0],
            pcPrice=config['price'].values[0]
        )
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))

        # return PCConfiguration(
        #     cpu='Intel Core i9-12900K',
        #     motherboard='Asus PRIME Z690M-PLUS D4',
        #     gpu='Radeon RX 6800 XT',
        #     ram='Corsair Vengeance LPX 32GB',
        #     psu='Corsair RM850x',
        #     type='Professional',
        #     price=2500.00
        # )

# if __name__ == "__main__":
#
#     uvicorn.run(app, host="127.0.0.1", port=8000)
