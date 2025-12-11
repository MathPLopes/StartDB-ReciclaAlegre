import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Card } from '../components/Card';
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
                <div className="logo">♻ ReciclAlegre</div>

                <nav>
                    <Link to="/login">Login</Link>
                    <Link to="/cadastro">Cadastro</Link>
                    <Link to="/sobre">Sobre</Link>
                </nav>

                <button id="toggle-theme" className="btn-theme">🌙</button>
            </header>

             <section className="grid-3">
            <Card title="Coleta rápida" text="Solicite a coleta dos seus resíduos em minutos." />
            <Card title="Reciclagem inteligente" text="Seu lixo ganha um novo destino sustentável." />
            <Card title="Acompanhe tudo" text="Veja seu impacto positivo no meio ambiente." />
</section>

        </div>
    );
};

export default Home;
