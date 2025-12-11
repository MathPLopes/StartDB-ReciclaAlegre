import React, { useEffect } from "react";
import "../assets/style.css";

const Sobre: React.FC = () => {

    useEffect(() => {
        const toggleBtn = document.getElementById("toggle-theme");
        if (toggleBtn) {
            toggleBtn.addEventListener("click", () => {
                document.body.classList.toggle("dark");
            });
        }
    }, []);

    return (
        <div>

            <header className="sobre-hero">
                <h1>Sobre o Reciclalegre</h1>
                <p>Conectando pessoas e sustentabilidade.</p>

                <button
                    id="toggle-theme"
                    className="btn-theme"
                    style={{ position: "absolute", right: "20px", top: "20px" }}
                >
                    🌙
                </button>
            </header>

            <section className="sobre-container fade">

                <div className="card">
                    <h2>Missão</h2>
                    <p>Promover a reciclagem e facilitar o descarte consciente.</p>
                </div>

                <div className="card">
                    <h2>Visão</h2>
                    <p>Ser referência em gestão sustentável de resíduos no Brasil.</p>
                </div>

                <div className="card">
                    <h2>Valores</h2>
                    <p>Consciência ambiental, responsabilidade, inovação.</p>
                </div>

            </section>

        </div>
    );
};

export default Sobre;
