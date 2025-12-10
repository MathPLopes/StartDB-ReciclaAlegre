import { useAuth } from "./hooks/useAuth"

export function AuthDebugger() {
    const { user, token, loading, isAuthenticated, login, logout, register } = useAuth()

    const fakeRegisterPayload = {
        email: "seuemail@test.com",
        senha: "senha123",
        tipoUsuario: "GERADOR",
        perfil: {
            nome: "Usuário Teste",
            telefone: "99999-9999",
            endereco: {
                cep: "99999-000",
                logradouro: "Rua Teste",
                numero: "123",
                bairro: "Centro",
                cidade: "Cidade Teste",
                estado: "ST",
                pais: "BR"
            }
        }
    };

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

            <button
                onClick={() => register(fakeRegisterPayload)}
                disabled={loading}
                style={{ marginLeft: 10 }}
            >
                Testar cadastro
            </button>

            <button onClick={() => logout()} style={{ marginLeft: 10 }}>
                Logout
            </button>
        </div>
    )
}