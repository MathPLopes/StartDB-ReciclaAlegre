import { useEffect, useState } from "react"
import { careggarSolicitacoes } from "../components/auth/api"
import type { SolicitacaoResponseDTO } from "../components/auth/AuthContext.types"

export default function ListaSolicitacoes({ token }: { token: string }) {
    const [solicitacoes, setSolicitacoes] = useState<SolicitacaoResponseDTO[]>([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        async function fetchData() {
            try {
                const dados = await careggarSolicitacoes(token)
                setSolicitacoes(dados)
            } catch (err: any) {
                setError(err.message || "Erro ao carregar solicitações")
            } finally {
                setLoading(false)
            }
        }

        fetchData()
    }, [token])

    if (loading) return <p>Carregando...</p>
    if (error) return <p style={{ color: "red" }}>{error}</p>

    return (
        <div>
            <h2>Solicitações</h2>

            {solicitacoes.length === 0 ? (
                <p style={{ opacity: 0.6 }}>Sem solicitações para serem listadas.</p>
            ) : (
                <ul>
                    {solicitacoes.map((s) => (
                        <li key={s.id}>
                            <strong>ID:</strong> {s.id} — {s.descricao}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    )
}
