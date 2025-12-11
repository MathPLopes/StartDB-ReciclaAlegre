import { useState, useEffect, useMemo } from 'react'
import type { AuthProviderProps, UsuarioResponseDTO, UsuarioRequestDTO } from './AuthContext.types'
import { registrarUsuario, loginUsuario, fetchUser } from "./auth.helpers";
import { AuthContext } from './AuthContext'

const TOKEN_KEY = 'jwt_token'

export function AuthProvider({ children }: AuthProviderProps) {
    const [token, setToken] = useState<string | null>(() => localStorage.getItem(TOKEN_KEY))
    const [user, setUser] = useState<UsuarioResponseDTO | null>(null)
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        if (token) localStorage.setItem(TOKEN_KEY, token)
        else localStorage.removeItem(TOKEN_KEY)
    }, [token])

    useEffect(() => {
        async function init() {
            if (!token) {
                setLoading(false)
                return
            }

            try {
                const me = await fetchUser(token)
                setUser(me)
            } catch (error) {
                console.error("Token inválido:", error)
                setToken(null)
            }

            setLoading(false)
        }
        init()
    }, [token])


    const register = async (data: UsuarioRequestDTO) => {
        console.log(data)
        try {
            await registrarUsuario(data)
        } catch (error) {
            console.error('Erro ao registrar usuário:', error)
            throw error
        }
    }


    const login = async (email: string, senha: string) => {
        console.log(email,senha)
        try {
            const token = await loginUsuario({ email, senha })
            setToken(token)
            const me = await fetchUser(token)
            setUser(me)
        } catch (error) {
            console.error('Erro ao fazer login:', error)
            throw error
        }
    }


    const logout = async () => {
        setToken(null)
        setUser(null)
    }

    const value = useMemo(
        () => ({
            user,
            token,
            loading,
            isAuthenticated: Boolean(user),
            register,
            login,
            logout
        }),
        [user, token, loading]
    )

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}
