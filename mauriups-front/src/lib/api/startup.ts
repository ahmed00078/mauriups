export const startupApi = {
    getAll: async () => {
    const response = await fetch('http://localhost:8000/api/startups');
      return response.json();
    },
    
    getById: async (id: string) => {
      const response = await fetch(`http://localhost:8000/api/startups/${id}`);
      return response.json();
    },
};