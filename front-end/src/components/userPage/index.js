import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

const mockBuilds = [
    {
        id: 1,
        name: 'Gaming PC',
        cpu: 'Intel Core i9',
        motherboard: 'ASUS ROG Maximus XIII Hero',
        gpu: 'NVIDIA GeForce RTX 3080',
        psu: 'Corsair RM850x',
        ram: 'Corsair Vengeance 32GB',
        type: 'Gaming',
        price: 2500,
        rating: 80
    },
    {
        id: 2,
        name: 'Office PC',
        cpu: 'Intel Core i5',
        motherboard: 'Gigabyte B560M DS3H',
        gpu: 'Integrated',
        psu: 'Cooler Master 500W',
        ram: 'Kingston 16GB',
        type: 'Office',
        price: 800,
        rating: 70
    },
    {
        id: 3,
        name: 'Multimedia PC',
        cpu: 'AMD Ryzen 7',
        motherboard: 'MSI B550M PRO-VDH',
        gpu: 'AMD Radeon RX 6800',
        psu: 'EVGA 750W',
        ram: 'G.Skill Ripjaws 32GB',
        type: 'Multimedia',
        price: 1500,
        rating: 85
    },
];

const UserPage = () => {
    const [user, setUser] = useState({
        firstName: 'John',
        lastName: 'Doe',
        email: 'john.doe@example.com',
    });

    const [builds, setBuilds] = useState(mockBuilds);

    const handleRatingChange = (id, newRating) => {
        setBuilds(builds.map(build =>
            build.id === id ? { ...build, rating: newRating } : build
        ));
    };

    const saveRating = (id) => {
        // Save the rating to the backend or state
        console.log(`Rating for build ${id} saved!`);
    };

    return (
        <div className="container mt-5">
            <div className="row">
                <div className="col-md-4 text-center">
                    <img
                        src="https://via.placeholder.com/150"
                        alt="User"
                        className="img-fluid rounded-circle mb-3"
                    />
                </div>
                <div className="col-md-8">
                    <h3>{user.firstName} {user.lastName}</h3>
                    <p>{user.email}</p>
                </div>
            </div>
            <div className="row mt-4">
                <div className="col-12">
                    <h4>My Builds</h4>
                    <ul className="list-group">
                        {builds.map(build => (
                            <li key={build.id} className="list-group-item">
                                <div className="d-flex justify-content-between align-items-center">
                                    <div>
                                        <h5>{build.name}</h5>
                                        <p>CPU: {build.cpu}</p>
                                        <p>Motherboard: {build.motherboard}</p>
                                        <p>GPU: {build.gpu}</p>
                                        <p>PSU: {build.psu}</p>
                                        <p>RAM: {build.ram}</p>
                                        <p>Type: {build.type}</p>
                                        <p>Price: ${build.price}</p>
                                        <p>Rating: {build.rating}/100</p>
                                    </div>
                                    <div className="d-flex align-items-center">
                                        <input
                                            type="number"
                                            className="form-control"
                                            value={build.rating}
                                            min="0"
                                            max="100"
                                            onChange={(e) => handleRatingChange(build.id, parseInt(e.target.value))}
                                        />
                                        <button
                                            className="btn btn-primary ml-2"
                                            onClick={() => saveRating(build.id)}
                                        >
                                            Save
                                        </button>
                                    </div>
                                </div>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default UserPage;
