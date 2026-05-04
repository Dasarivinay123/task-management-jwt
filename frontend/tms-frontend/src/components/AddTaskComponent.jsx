import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { createTask, getTaskById, updateTask } from '../services/UserService';

const AddTaskComponent = () => {

    const [taskname, setTaskname] = useState('');
    const navigate = useNavigate();
    const { id } = useParams(); // get taskId

    const userId = localStorage.getItem("userId");

    // LOAD DATA IF UPDATE
    useEffect(() => {
        if (id) {
            getTaskById(userId, id)
                .then((response) => {
                    setTaskname(response.data.data.taskname);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }, [id]);

    const handleSubmit = (e) => {
        e.preventDefault();

        const taskData = { taskname };

        if (id) {
            //UPDATE FLOW
            updateTask(userId, id, taskData)
                .then(() => {
                    alert("Task updated successfully");
                    navigate("/tasks");
                })
                .catch((error) => {
                    console.log(error);
                });
        } else {
            //ADD FLOW
            createTask(userId, taskData)
                .then(() => {
                    alert("Task added successfully");
                    navigate("/tasks");
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-5">
                    <div className="card p-4 shadow">

                        <h3 className="text-center mb-3">
                            {id ? "Update Task" : "Add Task"}
                        </h3>

                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label className="form-label">Task Name</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    value={taskname}
                                    onChange={(e) => setTaskname(e.target.value)}
                                    required
                                />
                            </div>

                            <button
                                type="submit"
                                className={`btn w-100 ${id ? "btn-warning" : "btn-success"}`}
                            >
                                {id ? "Update" : "Submit"}
                            </button>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddTaskComponent;