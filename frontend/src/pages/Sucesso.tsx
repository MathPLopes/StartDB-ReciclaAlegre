
import { useNavigate } from "react-router-dom";
import styles from "../css/Sucesso.module.css";

export default function Sucesso() {
  const navigate = useNavigate();

  const handleLoginRedirect = () => {
    navigate("/login");
  };

  return (
    <div className={styles.container}>
      <div className={styles.card}>
        <h2 className={styles.title}>Conta criada com sucesso!</h2>
        <p className={styles.message}>
          Sua conta foi registrada com sucesso. Agora você pode fazer login.
        </p>
        <button className={styles.button} onClick={handleLoginRedirect}>
          Ir para Login
        </button>
      </div>
    </div>
  );
}
