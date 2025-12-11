import { useContext } from "react"
import { AuthContext } from "./auth/AuthContext"
import styles from "../css/Header.module.css"
import { useNavigate } from "react-router-dom"

export default function Header() {
  const auth = useContext(AuthContext)
  const navigate = useNavigate()

  if (!auth) return null

  return (
    <header className={styles.header}>
      <span className={styles.username}>
        Olá, {auth.user?.perfil?.nome || "Usuário"}
      </span>

      <button
        className={styles.addButton}
        onClick={() => navigate("/AdicionarColeta")}
      >
        Adicionar Coleta
      </button>
    </header>
  );
}
