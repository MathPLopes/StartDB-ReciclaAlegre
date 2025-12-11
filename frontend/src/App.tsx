import { BrowserRouter, Routes, Route } from "react-router-dom"
import { AuthProvider } from "./components/auth/AuthProvider"
import { PrivateRoute } from "./routes/PrivateRoute"
import { AuthDebugger } from "./test"
import Sobre from "./pages/Sobre"
import Home from "./pages/Home"
import Login from "./pages/Login"
import Cadastro from "./pages/Cadastro"

export default function App() {
    return (
      <AuthProvider>
          <BrowserRouter>
              <Routes>
                  <Route path="/" element={<Home />} />
                 <Route path="/sobre" element={<Sobre />} />
                  <Route path="/cadastro" element={<Cadastro />} />
                  <Route path="/login" element={<Login />} />

                  <Route element={<PrivateRoute />}>
                  </Route>
              </Routes>
          </BrowserRouter>
      </AuthProvider>
    )
}