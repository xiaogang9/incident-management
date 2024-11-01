import React, { useState, useEffect} from 'react';
import IncidentList from './components/IncidentList';
import IncidentForm from './components/IncidentForm';
import api from './services/api';

function App() {
    const [incidents, setIncidents] = useState([]);
    const [selectIncident, setSelectedIncident] = useState(null);

    const fetchIncidents = async() => {
        const response = await api.get('/incidents');
        setIncidents(response.data.content);
    }

    useEffect(() => {
        fetchIncidents();
    }, []);

    const handleAddIncident = async (incident) => {
        await api.post('/incidents', incident);
        fetchIncidents();
    };

    const handleUpdateIncident = async (id, incident) => {
        await api.put('/incidents/${id}', incident);
        fetchIncidents();
        setSelectedIncident(null);
    };

    const handleDeleteIncident = async (id) => {
        await api.delete('/incidents/${id}');
        fetchIncidents();
    };

    return (
        <div className="App">
            <h1>Incident Management System</h1>
            <IncidentForm 
                onAddIncident={handleAddIncident}
                onUpdateIncident={handleUpdateIncident}
                selectedIncident={selectedIncident}
            />
            <IncidentList 
                incidents={incidents}
                onEdit={setSelectedIncident}
                onDelete={handleDeleteIncident}
            />
        </div>
    );
}

export default App;