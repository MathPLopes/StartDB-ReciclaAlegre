import { useState, useContext } from "react"
import { AuthContext } from "../components/auth/AuthContext"
import type { UsuarioRequestDTO } from "../components/auth/AuthContext.types"
import styles from "../css/Cadastro.module.css"
import { useNavigate } from "react-router-dom"

export default function Cadastro() {
    const auth = useContext(AuthContext)
    const navigate = useNavigate()

    const [form, setForm] = useState<UsuarioRequestDTO>({
        email: "",
        senha: "",
        tipoUsuario: "INDEFINIDO",
        perfil: {
            nome: "",
            telefone: "",
            endereco: {
                cep: "",
                logradouro: "",
                numero: "",
                bairro: "",
                cidade: "",
                estado: "",
                pais: "Brasil",
            },
        },
    });

    const [loading, setLoading] = useState(false)
    const [error, setError] = useState<string | null>(null)
    const [success, setSuccess] = useState<string | null>(null)

    if (!auth) {
        return <p>AuthContext não encontrado</p>
    }

    const handleChange = (path: string, value: string) => {
        setForm((prev) => {
            const updated = structuredClone(prev)
            const keys = path.split(".")
            let ref: any = updated
            for (let i = 0; i < keys.length - 1; i++) {
                ref = ref[keys[i]]
            }
            ref[keys[keys.length - 1]] = value;
            return updated
        })
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        setLoading(true)
        setError(null)
        setSuccess(null)

        try {
            await auth.register(form)
            setSuccess("Conta criada com sucesso! Agora faça login.")
            navigate("/Sucesso")
        } catch (err: unknown) {
            if (err instanceof Error) {
                if (err.message.includes("{") && err.message.includes("}")) {
                    setError("Preencha todos os campos corretamente.")
                } else {
                    setError(err.message)
                }
            } else {
                setError("Erro desconhecido ao registrar.")
            }
        } finally {
            setLoading(false)
        }
    }

    return (
        <div className={styles.container}>
            <form className={styles.form} onSubmit={handleSubmit}>
                <p className={styles.login}>
                    <h2 className={styles.title}>Criar Conta</h2>
                    Já é cadastrado?{" "}
                    <span className={styles.loginLink} onClick={() => navigate("/Login")}>Logar</span>
                </p>

                {error && <p className={styles.error}>{error}</p>}
                {success && <p className={styles.success}>{success}</p>}

                <div className={styles.grid2}>
                    <div className={styles.group}>
                        <label>Nome</label>
                        <input
                            type="text"
                            value={form.perfil.nome}
                            onChange={(e) => handleChange("perfil.nome", e.target.value)}
                        />
                    </div>

                    <div className={styles.group}>
                        <label>Telefone</label>
                        <input
                            type="text"
                            value={form.perfil.telefone}
                            onChange={(e) => handleChange("perfil.telefone", e.target.value)}
                        />
                    </div>
                </div>

                <div className={styles.grid2}>
                    <div className={styles.group}>
                        <label>Email</label>
                        <input
                            type="email"
                            value={form.email}
                            onChange={(e) => handleChange("email", e.target.value)}
                        />
                    </div>

                    <div className={styles.group}>
                        <label>Senha</label>
                        <input
                            type="password"
                            value={form.senha}
                            onChange={(e) => handleChange("senha", e.target.value)}
                        />
                    </div>
                </div>

                <h3 className={styles.subtitle}>Endereço</h3>

                <div className={styles.grid3}>
                    <div className={styles.group}>
                        <label>CEP</label>
                        <input
                            type="text"
                            value={form.perfil.endereco.cep}
                            onChange={(e) => handleChange("perfil.endereco.cep", e.target.value)}
                        />
                    </div>

                    <div className={styles.group}>
                        <label>Número</label>
                        <input
                            type="text"
                            value={form.perfil.endereco.numero}
                            onChange={(e) => handleChange("perfil.endereco.numero", e.target.value)}
                        />
                    </div>

                    <div className={styles.group}>
                        <label>Estado</label>
                        <input
                            type="text"
                            value={form.perfil.endereco.estado}
                            onChange={(e) => handleChange("perfil.endereco.estado", e.target.value)}
                        />
                    </div>
                </div>

                <div className={styles.grid2}>
                    <div className={styles.group}>
                        <label>Logradouro</label>
                        <input
                            type="text"
                            value={form.perfil.endereco.logradouro}
                            onChange={(e) => handleChange("perfil.endereco.logradouro", e.target.value)}
                        />
                    </div>

                    <div className={styles.group}>
                        <label>Bairro</label>
                        <input
                            type="text"
                            value={form.perfil.endereco.bairro}
                            onChange={(e) => handleChange("perfil.endereco.bairro", e.target.value)}
                        />
                    </div>
                </div>

                <div className={styles.grid2}>
                    <div className={styles.group}>
                        <label>Cidade</label>
                        <input
                            type="text"
                            value={form.perfil.endereco.cidade}
                            onChange={(e) => handleChange("perfil.endereco.cidade", e.target.value)}
                        />
                    </div>

                    <div className={styles.group}>
                        <label>País</label>
                        <input
                            type="text"
                            value={form.perfil.endereco.pais}
                            onChange={(e) => handleChange("perfil.endereco.pais", e.target.value)}
                        />
                    </div>
                </div>

                <button type="submit" className={styles.button} disabled={loading}>
                    {loading ? "Enviando..." : "Cadastrar"}
                </button>
            </form>
        </div>
    );
}
