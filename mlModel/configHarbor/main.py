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
    type: str
    price: float


def generate_random_combination(type, max_price):
    cpu, gpu, ram, psu, mb, combination = None, None, None, None, None, None
    price = 0
    while True:
        cpu = cpu_df.sample()
        gpu = gpu_df.sample()
        ram = ram_df.sample()
        psu = ps_df.sample()
        mb = mb_df.sample()
        price = cpu['price'].values[0] + mb['price'].values[0] + gpu['price'].values[0] + ram['price'].values[0] + \
                psu['price'].values[0]

        combination = pd.DataFrame([{
            'cpu': cpu['cpuName'].values[0],
            'motherboard': mb['name'].values[0],
            'gpu': gpu['gpuName'].values[0],
            'ram': ram['name'].values[0],
            'psu': psu['name'].values[0],
            'type': type,
            'price': price
        }])

        rating = model.predict(combination)[0]
        rating_threshold = types.get(type)
        if check_compatibility(cpu, mb, gpu, psu, ram) and max_price >= price >= (
                max_price * 0.7) and rating >= rating_threshold:
            break

    return combination


def check_compatibility(cpu, motherboard, gpu, psu, ram):
    if cpu['socket'].values.tolist()[0] != motherboard['socket'].values.tolist()[0]:
        return False
    if cpu['TDP'].values.tolist()[0] + gpu['TDP'].values.tolist()[0] > psu['wattage'].values.tolist()[0]:
        return False
    if ram['planks'].values.tolist()[0] > motherboard['memory_slots'].values.tolist()[0]:
        return False
    return True


@app.post("/config", response_model=PCConfiguration)
def get_recommendation(request: PCRequest):
    try:
        config = generate_random_combination(request.pcType, request.pcPrice)
        return PCConfiguration(
            cpu=config['cpu'].values[0],
            motherboard=config['motherboard'].values[0],
            gpu=config['gpu'].values[0],
            ram=config['ram'].values[0],
            psu=config['psu'].values[0],
            type=request.pcType,
            price=request.pcPrice
        )

        # return PCConfiguration(
        #     cpu='Intel Core i9-12900K',
        #     motherboard='Asus PRIME Z690M-PLUS D4',
        #     gpu='Radeon RX 6800 XT',
        #     ram='Corsair Vengeance LPX 32GB',
        #     psu='Corsair RM850x',
        #     type='Professional',
        #     price=2500.00
        # )
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))

# if __name__ == "__main__":
#
#     uvicorn.run(app, host="127.0.0.1", port=8000)
