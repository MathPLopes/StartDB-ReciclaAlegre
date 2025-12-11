import { useState, useContext } from "react"
import { AuthContext } from "../components/auth/AuthContext"
import styles from "../css/Login.module.css"
import { useNavigate } from "react-router-dom"

export default function LoginPage() {
  const auth = useContext(AuthContext);
  const [email, setEmail] = useState("")
  const [senha, setSenha] = useState("")
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)
  const navigate = useNavigate()

  if (!auth) {
    return <p>AuthContext não encontrado</p>
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setLoading(true)
    setError(null)

    try {
      await auth.login(email, senha)
      navigate("/Lista");
    } catch (err: any) {
      setError(err.message || "Erro ao fazer login")
    } finally {
      setLoading(false)
    }
  };

  return (
    <div className={styles.container}>
      <form className={styles.card} onSubmit={handleSubmit}>
        <h1 className={styles.title}>Bem-vindo!</h1>

        {error && <p className={styles.error}>{error}</p>}

        <div className={styles.inputGroup}>
          <label>Email</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className={styles.input}
          />
        </div>

        <div className={styles.inputGroup}>
          <label>Senha</label>
          <input
            type="password"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            required
            className={styles.input}
          />
        </div>

        <button type="submit" disabled={loading} className={styles.button}>
          {loading ? "Entrando..." : "Entrar"}
        </button>

        <p className={styles.register}>
          Não tem uma conta?{" "}
          <span className={styles.registerLink} onClick={() => navigate("/Cadastro")}>Cadastre-se</span>
        </p>
      </form>
    </div>
  )
}
