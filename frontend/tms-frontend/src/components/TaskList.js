import React, { useEffect, useState } from 'react';
import { getAllTasks } from '../services/UserService';
import { deleteTask } from '../services/UserService';
import { updateTask } from '../services/UserService';
import { useNavigate } from 'react-router-dom';


const TaskList = () => {

    const [tasks, setTasks] = useState([]);
    const navigate = useNavigate();
    const userId = 1;

    useEffect(() => {
        fetchTasks();
    }, []);

    const fetchTasks = () => {
        const userId = localStorage.getItem("userId");
        getAllTasks(userId)
            .then((response) => {
                console.log(response.data);
                setTasks(response.data.data);
            })
            .catch((error) => {
                console.log(error);
            });
    };
    const handleDelete = (taskId) => {
        const userId = localStorage.getItem("userId");
        deleteTask(userId, taskId)
            .then((response) => {
                alert("Task deleted");
                fetchTasks();
            })
            .catch((error) => {
                console.log(error);
            });
    }
    const handleUpdate = (task) => {
        navigate(`/edit-task/${task.id}`);
    };
    return (
      <div className="container mt-4" style={{ paddingBottom: "70px" }}>
            <h2 className="text-center">My Tasks</h2>
            <div className="d-flex justify-content-between align-items-center mb-3">
                <h2>My Tasks</h2>

                <button
                    className="btn btn-primary"
                    onClick={() => navigate("/add-task")}
                >
                    Add Task
                </button>
            </div>
            <table className="table table-striped table-bordered align-middle text-center">
                <thead className="table-dark">
                    <tr>
                        <th>Task Id</th>
                        <th>Task Name</th>
                        <th style={{ width: "200px" }}>Actions</th>
                    </tr>
                </thead>

                <tbody>
                    {tasks.map((task) => (
                        <tr key={task.id}>
                            <td>{task.id}</td>
                            <td>{task.taskname}</td>

                            <td>
                                <div className="d-flex justify-content-center gap-2">
                                    <button
                                        className="btn btn-warning btn-sm"
                                        onClick={() => handleUpdate(task)}
                                    >
                                        Update
                                    </button>

                                    <button
                                        className="btn btn-danger btn-sm"
                                        onClick={() => handleDelete(task.id)}
                                    >
                                        Delete
                                    </button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default TaskList;