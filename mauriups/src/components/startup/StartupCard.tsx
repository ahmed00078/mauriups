import { Startup } from '../../types/startup';

interface StartupCardProps {
  startup: Startup;
}

const StartupCard: React.FC<StartupCardProps> = ({ startup }) => {
  return (
    <div className="rounded-lg shadow-md p-6 hover:shadow-lg transition-shadow">
      <div className="flex items-center gap-4">
        <img
          src={startup.logo || "/api/placeholder/80/80"}
          alt={startup.name}
          className="w-20 h-20 rounded-full object-cover"
        />
        <div>
          <h3 className="text-xl font-semibold">{startup.name}</h3>
          <p className="text-gray-600">{startup.sector}</p>
        </div>
      </div>
      <p className="mt-4 text-gray-700 line-clamp-3">{startup.description}</p>
      <div className="mt-4 flex justify-between items-center">
        <span className="text-sm text-gray-500">{startup.location}</span>
        <button className="text-blue-600 hover:underline">Voir plus</button>
      </div>
    </div>
  );
};

export { StartupCard };