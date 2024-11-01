import React from 'react'

function IncidentList({ incidents, onEdit, onDelete}) {
    return (
        <div>
            <h2>Incident List</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {incidents.map((incident) =>(
                        <tr key={incident.id}>
                            <td>{incident.id}</td>
                            <td>{incident.title}</td>
                            <td>{incident.description}</td>
                            <td>
                                <button onClick={() => onEdit(incident)}>Edit</button>
                                <button onClick={() => onDelete(incident.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default IncidentList;