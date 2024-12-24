const SearchFilters = () => {
    return (
        <div className="space-y-4 p-4 bg-gray-50 rounded-lg">
            <div>
                <label htmlFor="secteur" className="block text-sm font-medium text-gray-700">
                    Secteur
                </label>
                <select id="secteur" className="mt-1 block w-full rounded-md border-gray-300">
                    <option>Tous les secteurs</option>
                    <option>Tech</option>
                    <option>Fintech</option>
                    <option>E-commerce</option>
                </select>
            </div>
            <div>
                <label htmlFor="localisation" className="block text-sm font-medium text-gray-700">
                    Localisation
                </label>
                <select id="localisation" className="mt-1 block w-full rounded-md border-gray-300">
                    <option>Toute la Tunisie</option>
                    <option>Tunis</option>
                    <option>Sfax</option>
                    <option>Sousse</option>
                </select>
            </div>
        </div>
    );
};

export { SearchFilters };