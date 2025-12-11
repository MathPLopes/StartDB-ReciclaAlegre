import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import "../assets/style.css";

const Home: React.FC = () => {

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
                <div className="logo">♻ Reciclalegre</div>

                <nav>
                    <Link to="/login">Login</Link>
                    <Link to="/cadastro">Cadastro</Link>
                    <Link to="/sobre">Sobre</Link>
                </nav>

                <button id="toggle-theme" className="btn-theme">🌙</button>
            </header>

            <section className="home-hero fade">
                <div className="hero-text">
                    <h1>Transforme resíduos em impacto positivo</h1>
                    <p>Facilitamos sua rotina, ajudamos o planeta.</p>
                    <Link to="/cadastro" className="btn-hero">Começar agora</Link>
                </div>

                <div className="hero-image">
                    <img src="/assets/img/reciclagem.png" alt="Reciclagem" />
                </div>
            </section>

            <section className="grid-3">

                <div className="card">
                    <h3>Coleta rápida</h3>
                    <p>Solicite a coleta dos seus resíduos em minutos.</p>
                </div>

                <div className="card">
                    <h3>Reciclagem inteligente</h3>
                    <p>Seu lixo ganha um novo destino sustentável.</p>
                </div>

                <div className="card">
                    <h3>Acompanhe tudo</h3>
                    <p>Veja seu impacto positivo no meio ambiente.</p>
                </div>

            </section>

        </div>
    );
};

export default Home;
