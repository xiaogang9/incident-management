import React, { useState, useEffect } from 'react';

function IncidentForm({ onAddIncident, onUpdateIncident, selectedIncident }) {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');

    useEffect(() => {
        if(selectedIncident) {
            setTitle(selectedIncident.title);
            setDescription(selectedIncident.description);
        } else {
            setTitle('');
            setDescription('');
        }
    }, [selectedIncident]);

    const handleSubmit = (e) => {
        e.preventDefault();
        const incident = { title, description };

        if (selectedIncident) {
            onUpdateIncident(selectedIncident.id, incident);
        } else {
            onAddIncident(incident);
        }

        setTitle('');
        setDescription('');
    };

    return (
        <div>
            <h2>{selectedIncident ? 'Edit Incident' : 'Add Incident'}</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <lable>Title:</lable><br />
                    <input 
                        type="text" 
                        value={title} 
                        onChange={(e) => setTitle(e.target.value)} 
                        required>
                    </input>
                </div>
                <div>
                    <lable>Description:</lable><br />
                    <textarea 
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}>
                    </textarea>
                </div>
                <button type="submit">{selectedIncident ? 'Update' : 'Add'}</button>
                {selectedIncident && (
                    <button type="button" onClick={() => setTitle('') & setDescription('')}>
                        Cancel
                    </button>
                )}
            </form>
        </div>
    );
}

export default IncidentForm;