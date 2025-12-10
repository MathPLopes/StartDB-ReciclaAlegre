import React from 'react'
import { Routes, Route } from 'react-router-dom'
import Header from './components/Header'
import Footer from './components/Footer'
import Home from './pages/Home'
import Login from './pages/Login'
import About from './pages/Sobre'
import Collection from './pages/Coleta'
import Perfil from './pages/Perfil'
import Cadastro from './pages/Cadastro'


export default function App() {
return (

  <div className="app-root">

<Header />

<main>
  <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/about" element={<About />} />
      <Route path="/collection" element={<Coleta />} />
      <Route path="/profile" element={<Perfil />} />
  </Routes>

</main>

<Footer />
  </div>
)
}