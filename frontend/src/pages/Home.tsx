import React from 'react'
import { Link } from 'react-router-dom'

const Home:React.FC=() => {
    return (
        <div className="container">
            <h1>Bem-vindo ao ReciclaAlegre</h1>
            <p>Plataforma para agendamento de coletas e gerenciamento de pontos de reciclagem.</p>
            <Link to="/collection" className="btn btn-primary">Agendar Coleta</Link>
        </div>
    )
}

export default Home;