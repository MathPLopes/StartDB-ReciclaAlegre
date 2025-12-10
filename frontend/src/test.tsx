import { useAuth } from "./hooks/useAuth"

export function AuthDebugger() {
    const { user, token, loading, isAuthenticated, login, logout } = useAuth()

    return (
        <div style={{ padding: 20, border: "1px solid #ccc", margin: 20 }}>
            <h3>Auth Debugger</h3>

            <p><strong>loading:</strong> {loading ? "true" : "false"}</p>
            <p><strong>isAuthenticated:</strong> {isAuthenticated ? "true" : "false"}</p>
            <p><strong>token:</strong> {token ?? "null"}</p>

            <p><strong>user:</strong></p>
            <pre>{user ? JSON.stringify(user, null, 2) : "null"}</pre>

            <button
                onClick={() => login("seuemail@test.com", "senha123")}
                disabled={loading}
            >
                Testar login
            </button>

            <button onClick={() => logout()}>
                Logout
            </button>
        </div>
    )
}
