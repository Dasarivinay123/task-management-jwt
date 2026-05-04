import axiosInstance from "./axiosConfig";

export const getAllTasks = (userId) => {
    return axiosInstance.get(`/api/${userId}/tasks`);
};

export const createTask = (userId, taskData) => {
    return axiosInstance.post(`/api/${userId}/tasks`, taskData);
};
export const getTaskById = (userId, taskId) => {
    return axiosInstance.get(`/api/${userId}/tasks/${taskId}`);
};

export const updateTask = (userId, taskId, data) => {
    console.log(userId, taskId, data);
    return axiosInstance.put(`/api/${userId}/tasks/${taskId}`, data);
};

export const deleteTask = (userId, taskId) => {
    return axiosInstance.delete(`/api/${userId}/tasks/${taskId}`);
};