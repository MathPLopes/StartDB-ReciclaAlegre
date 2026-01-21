import React, { useContext, useEffect } from "react";
import { Link, NavLink } from "react-router-dom";
import { Card } from "../components/Card";
import "../assets/style.css";
import { AuthContext } from "../components/auth/AuthContext";

const Home: React.FC = () => {
  const auth = useContext(AuthContext);

  useEffect(() => {
    const toggleBtn = document.getElementById("toggle-theme");
    if (toggleBtn) {
      toggleBtn.addEventListener("click", () => {
        document.body.classList.toggle("dark");
      });
    }
  }, []);

  return (
    <div className="home-body">
      <header>
        <div className="logo">ReciclAlegre</div>

        <nav>
          {auth?.user ? (
            <>
              <NavLink to="/Perfil" style={{ fontWeight: "bold" }}>
                Olá, {auth?.user.perfil.nome || "Usuario"}
              </NavLink>
            </>
          ) : (
            <>
              <NavLink to="/login">Login</NavLink>
              <NavLink to="/cadastro">Cadastro</NavLink>
            </>
          )}
          <NavLink to="/sobre">Sobre</NavLink>
        </nav>

        <button id="toggle-theme" className="btn-theme">
          🌙
        </button>
      </header>

      <section className="grid-3">
        <Card
          title="Coleta rápida"
          text="Solicite a coleta dos seus resíduos em minutos."
        />
        <Card
          title="Reciclagem inteligente"
          text="Seu lixo ganha um novo destino sustentável."
        />
        <Card
          title="Acompanhe tudo"
          text="Veja seu impacto positivo no meio ambiente."
        />
      </section>
    </div>
  );
};

export default Home;
