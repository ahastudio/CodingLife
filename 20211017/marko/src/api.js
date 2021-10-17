import axios from 'axios';

const baseURL = 'http://localhost:8080';

export async function fetchTasks() {
  const { data } = await axios.get(`${baseURL}/tasks`);
  return data;
}

export async function createTask({ body }) {
  await axios.post(`${baseURL}/tasks`, {
    body,
  });
}

export async function updateTask({ id, status }) {
  await axios.put(`${baseURL}/tasks/${id}`, {
    status,
  });
}

export async function deleteTask({ id }) {
  await axios.delete(`${baseURL}/tasks/${id}`);
}
