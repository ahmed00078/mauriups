Une organisation complète des routes API pour mon backend Spring Boot.



```java
// Structure des packages
com.startup.directory
    ├── controller
    │   ├── AuthController
    │   ├── StartupController
    │   ├── UserController
    │   ├── TeamController
    │   ├── MediaController
    │   ├── JobController
    │   └── AdminController
    
// 1. Authentication Routes
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/login")                 // POST /api/auth/login
    @PostMapping("/register")              // POST /api/auth/register
    @PostMapping("/refresh-token")         // POST /api/auth/refresh-token
    @PostMapping("/forgot-password")       // POST /api/auth/forgot-password
    @PostMapping("/reset-password")        // POST /api/auth/reset-password
    @PostMapping("/logout")                // POST /api/auth/logout
}

// 2. Startup Routes
@RestController
@RequestMapping("/api/startups")
public class StartupController {
    @GetMapping                           // GET /api/startups
    @GetMapping("/{id}")                  // GET /api/startups/{id}
    @PostMapping                          // POST /api/startups
    @PutMapping("/{id}")                  // PUT /api/startups/{id}
    @DeleteMapping("/{id}")               // DELETE /api/startups/{id}
    @GetMapping("/search")                // GET /api/startups/search?query=
    @GetMapping("/sector/{sectorId}")     // GET /api/startups/sector/{sectorId}
    @GetMapping("/{id}/team")             // GET /api/startups/{id}/team
    @GetMapping("/{id}/jobs")             // GET /api/startups/{id}/jobs
    @GetMapping("/{id}/media")            // GET /api/startups/{id}/media
}

// 3. User Routes
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/profile")               // GET /api/users/profile
    @PutMapping("/profile")               // PUT /api/users/profile
    @PutMapping("/password")              // PUT /api/users/password
    @GetMapping("/notifications")         // GET /api/users/notifications
    @PutMapping("/notifications/{id}")    // PUT /api/users/notifications/{id}
}

// 4. Admin Routes
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    // Gestion des startups
    @GetMapping("/startups/pending")      // GET /api/admin/startups/pending
    @PutMapping("/startups/{id}/approve") // PUT /api/admin/startups/{id}/approve
    @PutMapping("/startups/{id}/reject")  // PUT /api/admin/startups/{id}/reject
    
    // Gestion des utilisateurs
    @GetMapping("/users")                 // GET /api/admin/users
    @PostMapping("/users")                // POST /api/admin/users
    @PutMapping("/users/{id}")            // PUT /api/admin/users/{id}
    @DeleteMapping("/users/{id}")         // DELETE /api/admin/users/{id}
    
    // Statistiques et rapports
    @GetMapping("/stats")                 // GET /api/admin/stats
    @GetMapping("/audit-logs")            // GET /api/admin/audit-logs
    @GetMapping("/reports/monthly")        // GET /api/admin/reports/monthly
}

// 5. Team Routes
@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @PostMapping("/{startupId}/members")   // POST /api/teams/{startupId}/members
    @PutMapping("/members/{id}")           // PUT /api/teams/members/{id}
    @DeleteMapping("/members/{id}")        // DELETE /api/teams/members/{id}
}

// 6. Job Routes
@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @GetMapping                           // GET /api/jobs
    @GetMapping("/{id}")                  // GET /api/jobs/{id}
    @PostMapping                          // POST /api/jobs
    @PutMapping("/{id}")                  // PUT /api/jobs/{id}
    @DeleteMapping("/{id}")               // DELETE /api/jobs/{id}
    @GetMapping("/search")                // GET /api/jobs/search
}

// 7. Media Routes
@RestController
@RequestMapping("/api/media")
public class MediaController {
    @PostMapping("/upload")               // POST /api/media/upload
    @DeleteMapping("/{id}")               // DELETE /api/media/{id}
    @PutMapping("/{id}")                  // PUT /api/media/{id}
}

// 8. Settings Routes (Admin)
@RestController
@RequestMapping("/api/settings")
public class SettingsController {
    @GetMapping                           // GET /api/settings
    @PutMapping                           // PUT /api/settings
    @GetMapping("/sectors")               // GET /api/settings/sectors
    @PostMapping("/sectors")              // POST /api/settings/sectors
    @DeleteMapping("/sectors/{id}")       // DELETE /api/settings/sectors/{id}
}

```

Pour implémenter ces routes efficacement, voici la structure de projet recommandée :

```bash
src/main/java/com/mauriups/mauriups/
├── config/
│   ├── SecurityConfig.java
│   ├── JwtConfig.java
│   └── WebConfig.java
├── controller/
│   └── [tous les controllers listés au-dessus]
├── model/
│   ├── User.java
│   ├── Startup.java
│   ├── TeamMember.java
│   ├── Job.java
│   └── etc...
├── repository/
│   ├── UserRepository.java
│   ├── StartupRepository.java
│   └── etc...
├── service/
│   ├── UserService.java
│   ├── StartupService.java
│   ├── AuthService.java
│   └── etc...
├── dto/
│   ├── request/
│   │   ├── LoginRequest.java
│   │   ├── StartupRequest.java
│   │   └── etc...
│   └── response/
│       ├── LoginResponse.java
│       ├── StartupResponse.java
│       └── etc...
├── security/
│   ├── JwtTokenProvider.java
│   └── UserPrincipal.java
└── exception/
    ├── ResourceNotFoundException.java
    ├── BadRequestException.java
    └── etc...
```

Pour commencer l'implémentation, je suggère cet ordre :

1. Configuration de base
   - Dépendances Maven/Gradle
   - Configuration de la base de données
   - Configuration de sécurité

2. Premières fonctionnalités à implémenter
   - Authentification (register/login)
   - CRUD basique des startups
   - Gestion des utilisateurs