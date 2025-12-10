import { BrowserRouter, Routes, Route } from "react-router-dom"
import { AuthProvider } from "./components/auth/AuthProvider"
import { PrivateRoute } from "./routes/PrivateRoute"


export default function App() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <Routes>

                    <Route path="/Login" element={<Login />} />
                    <Route path="/Cadastro" element={<Cadastro />} />
                    <Route path="/Sobre" element={<Sobre />} />
                    <Route element={<PrivateRoute />}>
                        <Route path="/" element={<Home />} />
                        <Route path="/Coleta" element={<Coleta />} />
                        <Route path="/Perfil" element={<Perfil />} />
                    </Route>
                </Routes>
            </BrowserRouter>
        </AuthProvider>
    )
}