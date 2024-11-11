import React, { useState } from 'react';
import axios from 'axios';

const Configurator = () => {
    const [pcType, setPcType] = useState('');
    const [pcPrice, setPriceRange] = useState(1000.0);
    const [pcUpgrade, setUpgradeable] = useState(false);
    const [pcOverclock, setOverclocking] = useState(false);
    const [configuration, setConfiguration] = useState(null);

    const pcTypes = [
        { value: 'Gaming', label: 'Gaming', description: 'High performance for gaming.' },
        { value: 'Professional', label: 'Professional', description: 'Suitable for professional work.' },
        { value: 'Office', label: 'Office', description: 'Basic office tasks.' },
        { value: 'Multimedia', label: 'Multimedia', description: 'Entertainment and media consumption.' },
        { value: 'All-in-One', label: 'All-in-One', description: 'Integrated system for multiple uses.' },
    ];

    const handleSubmit = async (e) => {
        // e.preventDefault();
        // const staticConfig = {
        //     cpu: 'Intel Core i7',
        //     motherboard: 'ASUS Prime Z390-A',
        //     gpu: 'NVIDIA GeForce RTX 3080',
        //     psu: 'Corsair RM750x',
        //     ram: '16GB DDR4',
        //     type: pcType,
        //     price: '$1500'
        // };
        // setConfiguration(staticConfig);

        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/config/getPCConfig', {
                pcType,
                pcPrice,
                pcUpgrade,
                pcOverclock,
            });
            console.log(response.data)
            setConfiguration(response.data);
        } catch (error) {
            console.error('Error fetching configuration:', error);
        }
    };

    return (
        <div className="container mt-5">
            <h2>PC Configurator</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label className="form-label">Select PC Type</label>
                    <select className="form-select" value={pcType}  onChange={(e) => setPcType(e.target.value)} required>
                        {pcTypes.map((type) => (
                            <option key={type.value} value={type.value}>{type.label}</option>
                        ))}
                    </select>
                    {pcType && (
                        <small className="form-text text-muted">
                            {pcTypes.find(type => type.value === pcType).description}
                        </small>
                    )}
                </div>
                <div className="mb-3">
                    <label className="form-label">Price Range</label>
                    <input
                        type="number"
                        className="form-control"
                        value={pcPrice}
                        onChange={(e) => setPriceRange(e.target.value)}
                        placeholder="e.g., $1500"
                        required
                    />
                </div>
                <div className="form-check mb-3">
                    <input
                        type="checkbox"
                        className="form-check-input"
                        checked={pcUpgrade}
                        onChange={(e) => setUpgradeable(e.target.checked)}
                        id="upgradeable"
                    />
                    <label className="form-check-label" htmlFor="upgradeable">Upgradeable</label>
                </div>
                <div className="form-check mb-3">
                    <input
                        type="checkbox"
                        className="form-check-input"
                        checked={pcOverclock}
                        onChange={(e) => setOverclocking(e.target.checked)}
                        id="overclocking"
                    />
                    <label className="form-check-label" htmlFor="overclocking">Overclocking</label>
                </div>
                <button type="submit" className="btn btn-primary">Get Configuration</button>
            </form>
            {configuration && (
                <div className="mt-5">
                    <h3>PC Configuration</h3>
                    <ul className="list-group">
                        <li className="list-group-item"><strong>CPU:</strong> {configuration.cpu}</li>
                        <li className="list-group-item"><strong>Motherboard:</strong> {configuration.motherboard}</li>
                        <li className="list-group-item"><strong>GPU:</strong> {configuration.gpu}</li>
                        <li className="list-group-item"><strong>PSU:</strong> {configuration.psu}</li>
                        <li className="list-group-item"><strong>RAM:</strong> {configuration.ram}</li>
                        <li className="list-group-item"><strong>Type:</strong> {configuration.pcType}</li>
                        <li className="list-group-item"><strong>Price:</strong> {configuration.pcPrice}</li>
                    </ul>
                </div>
            )}
        </div>
    );
};

export default Configurator;