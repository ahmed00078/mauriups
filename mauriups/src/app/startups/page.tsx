import { StartupCard } from '../../components/startup/StartupCard';

export default function StartupsPage() {
    return (
        <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
            <main className="md:col-span-3">
                <StartupCard startup={{ 
                    id: '1', 
                    name: 'Example Startup', 
                    description: 'This is an example startup.',
                    sector: 'Technology',
                    location: 'Mauritania',
                    team: [{ id: '1', name: 'John Doe', role: 'CEO' }]
                }} />
            </main>
        </div>
    );
}