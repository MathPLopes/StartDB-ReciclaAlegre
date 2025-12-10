import type {
    LoginDTO,
    UsuarioResponseDTO,
    SolicitacaoRequestDTO,
    SolicitacaoResponseDTO,
} from './AuthContext.types'

const BASE_URL = 'http://localhost:8080/api'

export async function loginUser(data: LoginDTO): Promise<{ token: string }> {
    const res = await fetch(`${BASE_URL}/auth`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
    })
    if (!res.ok) throw new Error('Login falhou')
    const token = await res.text() 
    return { token }
}

export async function logout(): Promise<void> {
    await fetch(`${BASE_URL}/auth/logout`, {
        method: 'POST',
    })
}


export async function getMe(token: string): Promise<UsuarioResponseDTO> {
    const res = await fetch(`${BASE_URL}/usuarios/perfil`, {
        headers: { Authorization: `Bearer ${token}` },
    })
    if (!res.ok) throw new Error('Não foi possível buscar usuário')
    return res.json()
}

export async function listaDeUsuarios(token: string): Promise<UsuarioResponseDTO[]> {
    const res = await fetch(`${BASE_URL}/usuarios`, {
        headers: { Authorization: `Bearer ${token}` },
    })
    if (!res.ok) throw new Error('Não foi possível listar usuários')
    return res.json()
}

export async function desabilitarUsuario(token: string): Promise<void> {
    const res = await fetch(`${BASE_URL}/usuarios`, {
        method: 'DELETE',
        headers: { Authorization: `Bearer ${token}` },
    })
    if (!res.ok) throw new Error('Falha ao desabilitar usuário')
}

export async function criarSolicitacao(
    data: SolicitacaoRequestDTO,
    token: string
): Promise<SolicitacaoResponseDTO> {
    const res = await fetch(`${BASE_URL}/solicitacoes`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(data),
    })
    if (!res.ok) throw new Error('Não foi possível criar solicitação')
    return res.json()
}

export async function careggarSolicitacoes(token: string): Promise<SolicitacaoResponseDTO[]> {
    const res = await fetch(`${BASE_URL}/solicitacoes`, {
        headers: { Authorization: `Bearer ${token}` },
    })
    if (!res.ok) throw new Error('Não foi possível carregar solicitações')
    return res.json()
}

export async function requisitarColeta(id: number, token: string): Promise<void> {
    const res = await fetch(`${BASE_URL}/solicitacoes/claim/${id}`, {
        method: 'POST',
        headers: { Authorization: `Bearer ${token}` },
    })
    if (!res.ok) throw new Error('Falha ao requisitar coleta')
}

export async function getSolicitacoesByUsuario(token: string): Promise<SolicitacaoResponseDTO[]> {
    const res = await fetch(`${BASE_URL}/solicitacoes/by-usuario`, {
        headers: { Authorization: `Bearer ${token}` },
    })
    if (!res.ok) throw new Error('Falha ao buscar solicitações do usuário')
    return res.json()
}
