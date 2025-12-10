import type { AuthContextType } from "../components/auth/AuthContext.ts"
import {AuthContext} from "../components/auth/AuthContext.ts"
import { useContext } from "react"

export function useAuth(): AuthContextType {
    const ctx = useContext(AuthContext)
    if (!ctx) throw new Error('useAuth must be used within an AuthProvider')
    return ctx
}
