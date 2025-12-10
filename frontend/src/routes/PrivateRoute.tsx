import { Navigate, Outlet } from "react-router-dom"
import { useAuth } from "../hooks/useAuth"

export function PrivateRoute() {
    const { isAuthenticated, loading } = useAuth()

    if (loading) return <p>Carregando!</p>

    return isAuthenticated ? <Outlet /> : <Navigate to="/login" />
}
