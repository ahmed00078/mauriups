// src/hooks/useStartups.ts
import { useState, useEffect } from "react";
import { startupApi } from "@/lib/api/startup";

export function useStartups() {
    const [startups, setStartups] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchStartups = async () => {
            try {
                const data = await startupApi.getAll();
                setStartups(data);
            } catch (err) {
                setError(err);
            } finally {
                setLoading(false);
            }
        };

        fetchStartups();
    }, []);

    return { startups, loading, error };
}
