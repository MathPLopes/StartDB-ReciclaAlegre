import { BrowserRouter, Routes, Route } from "react-router-dom"
import { AuthProvider } from "./components/auth/AuthProvider"
import { PrivateRoute } from "./routes/PrivateRoute"
import { AuthDebugger } from "./test"

export default function App() {
    return (
      <AuthProvider>
          <BrowserRouter>
              <Routes>
                  <Route path="/teste" element={<AuthDebugger />} />

                  <Route element={<PrivateRoute />}>
                      <Route path="/teste2" element={<AuthDebugger />} />
                  </Route>
              </Routes>
          </BrowserRouter>
      </AuthProvider>
    )
}