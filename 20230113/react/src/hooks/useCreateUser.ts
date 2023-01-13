export default function useCreateUser() {
  return {
    async createUser({ name, job }: {
      name: string;
      job: string;
    }) {
      const url = 'https://reqres.in/api/users';
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, job }),
      });
      const data = await response.json();
      return data;
    },
  };
}
