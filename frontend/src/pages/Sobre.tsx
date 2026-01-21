import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../assets/style.css";

const Sobre: React.FC = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const toggleBtn = document.getElementById("toggle-theme");

    const handleToggle = () => {
      document.body.classList.toggle("dark");
    };

    toggleBtn?.addEventListener("click", handleToggle);

    return () => {
      toggleBtn?.removeEventListener("click", handleToggle);
    };
  }, []);

  return (
    <div>
     <header className="sobre-hero-topbar">
  <button onClick={() => navigate("/")} className="btn-voltar">
    ⬅ Voltar
  </button>

  <h1 className="titulo-reciclalegre">Sobre o ReciclAlegre</h1>
</header>
      <section className="sobre-container fade">
        <div className="card">
          <h2>Missão</h2>
          <p>
            Facilitar o descarte consciente e responsável de materiais
            recicláveis, contribuindo para um ambiente mais limpo e saudável.
          </p>
        </div>

        <div className="card">
          <h2>Visão</h2>
          <p>
            Valorizar o trabalho dos recicladores — oferecendo visibilidade,
            dignidade e uma remuneração justa pelo serviço prestado.
          </p>
        </div>

        <div className="card">
          <h2>Valores</h2>
          <p>
            Consciência ambiental, responsabilidade e inovação. Estimular a
            participação cidadã: convencer pessoas e famílias de que reciclar é
            um ato coletivo e transformador.
          </p>
        </div>

        <div className="card">
          <h2>Como atuamos</h2>

          <h3>Conexão direta</h3>
          <p>
            Criamos uma ponte entre quem tem material reciclável e
            catadores/cooperativas locais, aproximando geradores e recicladores.
          </p>

          <h3>Acordos justos</h3>
          <p>
            O serviço de coleta deve ser remunerado de forma justa, reconhecendo
            que reciclagem é trabalho e merece valorização.
          </p>

          <h3>Respeito e dignidade</h3>
          <p>
            Reconhecemos os recicladores como agentes essenciais da
            sustentabilidade e lutamos contra a invisibilidade e a precarização
            deste trabalho.
          </p>

          <h3>Consciência ambiental</h3>
          <p>
            Incentivamos o descarte correto, a separação dos materiais
            recicláveis e o reaproveitamento, contribuindo para a redução de
            resíduos e o uso consciente dos recursos naturais.
          </p>
        </div>

        <div className="card">
          <h2>Por que existe o ReciclAlegre?</h2>

          <p>
            Muitas cidades, especialmente fora dos grandes centros, ainda não
            contam com coleta seletiva estruturada. Isso gera desperdício e
            reduz drasticamente a reciclagem.
          </p>

          <p>
            Queremos mudar essa realidade: levar às pessoas uma alternativa
            eficaz e humana, fazer chegar reciclagem e renda àqueles que mais
            precisam e, com isso, gerar impacto real no meio ambiente e na vida
            das pessoas.
          </p>
        </div>

        <div className="card">
          <h2>Nosso convite</h2>

          <p>
            Se você se preocupa com o destino do seu lixo, com o futuro do
            planeta ou com a dignidade de quem trabalha com reciclagem —
            junte-se a nós.
          </p>

          <p>
            Separando seu lixo reciclável, contratando a coleta ou ajudando a
            divulgar nosso trabalho: toda atitude conta.
          </p>

          <p>
            Reciclar é preciso, e com respeito a quem transforma — isso é
            ReciclAlegre.
          </p>
        </div>
      </section>
    </div>
  );
};

export default Sobre;
