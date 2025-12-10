export interface Endereco {
    cep: string
    logradouro: string
    numero: string
    bairro: string
    cidade: string
    estado: string
    pais: string
}

export interface PerfilRequestDTO {
    nome: string
    endereco: Endereco
    telefone: string
}

export interface UsuarioRequestDTO {
    email: string
    senha: string
    tipoUsuario: 'INDEFINIDO' | 'GERADOR' | 'COLETOR' | 'ADMINISTRADOR'
    perfil: PerfilRequestDTO
}

export interface PerfilResponseDTO {
    nome: string
    endereco: Endereco
    telefone: string
}

export interface UsuarioResponseDTO {
    id: number
    email: string
    ativo: boolean
    tipoUsuario: 'INDEFINIDO' | 'GERADOR' | 'COLETOR' | 'ADMINISTRADOR'
    perfil: PerfilResponseDTO
}

export interface LoginDTO {
    email: string
    senha: string
}


export interface MaterialDTO {
    tipoMaterial:  'PAPEL'|'PLASTICO'|'METAL'|'VIDRO'|'MADEIRA'| 'ELETRONICO'|'TECIDO'|'OLEO'|'ORGANICO'|'OUTROS'
    quantidadeMaterial: 'LEVE' | 'MEDIO' | 'PESADO' | 'MUITO_PESADO'
}

export interface SolicitacaoRequestDTO {
    materiais: MaterialDTO[]
    descricao: string
    endereco?: Endereco
}

export interface SolicitacaoResponseDTO {
    id: number
    gerador: string
    dataCriacao: string
    descricao: string
    endereco: Endereco
    situacao: 'ATIVO' | 'PENDENTE' | 'APROVADO' | 'CONCLUIDO' | 'CANCELADO'
    materiais: MaterialDTO[]
}



export interface AuthProviderProps {
    children: React.ReactNode
}



