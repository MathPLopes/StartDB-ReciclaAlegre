import { useEffect, useState, useContext } from "react";
import { AuthContext } from "../components/auth/AuthContext";
import {
  carregarSolicitacoesPorUsuario,
  atualizarSolicitacao,
} from "../components/auth/api";
import type {
  MaterialDTO,
  SolicitacaoRequestDTO,
  SolicitacaoResponseDTO,
} from "../components/auth/AuthContext.types";
import "../css/EditarSolicitacao.css";

const tiposMaterial: MaterialDTO["tipoMaterial"][] = [
  "PAPEL",
  "PLASTICO",
  "METAL",
  "VIDRO",
  "MADEIRA",
  "ELETRONICO",
  "TECIDO",
  "OLEO",
  "ORGANICO",
  "OUTROS",
];

const pesoParaQuantidade = (
  peso: number,
): MaterialDTO["quantidadeMaterial"] => {
  if (peso < 5) return "LEVE";
  if (peso < 15) return "MEDIO";
  if (peso < 30) return "PESADO";
  return "MUITO_PESADO";
};

const quantidadeParaPesoEstimado = (qtd: string): number => {
  switch (qtd) {
    case "LEVE":
      return 4;
    case "MEDIO":
      return 10;
    case "PESADO":
      return 20;
    case "MUITO_PESADO":
      return 35;
    default:
      return 0;
  }
};

interface MaterialComPeso {
  tipoMaterial: MaterialDTO["tipoMaterial"];
  peso: number;
}

export default function EditarSolicitacoes() {
  const auth = useContext(AuthContext);

  const [solicitacoes, setSolicitacoes] = useState<SolicitacaoResponseDTO[]>(
    [],
  );
  const [loading, setLoading] = useState(true);
  const [editandoId, setEditandoId] = useState<number | null>(null);
  const [formData, setFormData] = useState({
    descricao: "",
    materiais: [] as MaterialComPeso[],
  });

  useEffect(() => {
    if (!auth?.token) return;
    carregarSolicitacoesPorUsuario(auth.token)
      .then((data) => setSolicitacoes(data))
      .catch(() => console.error("Erro ao carregar"))
      .finally(() => setLoading(false));
  }, [auth?.token]);

  const getStatusColor = (status: string) => {
    if (status === "CONCLUIDO") return "green";
    if (status === "APROVADO") return "blue";
    if (status === "REJEITADO") return "red";
    return "orange";
  };

  const handleEditarClick = (solicitacao: any) => {
    setEditandoId(solicitacao.id);

    const materiaisConvertidos: MaterialComPeso[] = (
      solicitacao.materiais || []
    ).map((m: any) => ({
      tipoMaterial: m.tipoMaterial,
      peso: quantidadeParaPesoEstimado(m.quantidadeMaterial),
    }));

    setFormData({
      descricao: solicitacao.descricao || "",
      materiais: materiaisConvertidos,
    });
  };

  const handleCancelar = () => {
    setEditandoId(null);
    setFormData({ descricao: "", materiais: [] });
  };

  const handleAddMaterial = () => {
    setFormData((prev) => ({
      ...prev,
      materiais: [...prev.materiais, { tipoMaterial: "PAPEL", peso: 0 }],
    }));
  };

  const handleRemoveMaterial = (index: number) => {
    setFormData((prev) => ({
      ...prev,
      materiais: prev.materiais.filter((_, i) => i !== index),
    }));
  };

  const handleChangeMaterial = (
    index: number,
    field: keyof MaterialComPeso,
    value: any,
  ) => {
    setFormData((prev) => ({
      ...prev,
      materiais: prev.materiais.map((item, i) =>
        i === index ? { ...item, [field]: value } : item,
      ),
    }));
  };

  const handleAprovar = async (id: number) => {
    if (!auth?.token) return;
    setLoading(true);
    try {
      await fetch(`http://localhost:8080/api/solicitacoes/aprovar/${id}`, {
        method: "POST",
        headers: { Authorization: `Bearer ${auth.token}` },
      });

      const data = await carregarSolicitacoesPorUsuario(auth.token);
      setSolicitacoes(data);
    } catch (Error) {
      console.log(Error);
    } finally {
      setLoading(false);
    }
  };

  const handleSalvar = async () => {
    try {
      if (!auth?.token || !editandoId) return;

      if (formData.materiais.length === 0) {
        alert("Adicione ao menos um material.");
        return;
      }

      const dto: SolicitacaoRequestDTO = {
        descricao: formData.descricao,
        materiais: formData.materiais.map((m) => ({
          tipoMaterial: m.tipoMaterial,
          quantidadeMaterial: pesoParaQuantidade(m.peso),
        })),
      };

      const itemAtualizado = await atualizarSolicitacao(
        editandoId,
        dto,
        auth.token,
      );

      setSolicitacoes((prev) =>
        prev.map((s) => (s.id === editandoId ? itemAtualizado : s)),
      );

      setEditandoId(null);
      alert("Solicitação atualizada!");
    } catch (error) {
      console.error(error);
      alert("Erro ao atualizar solicitação");
    }
  };

  if (loading) return <p>Carregando...</p>;

  return (
    <div className="container">
      <h2>Gerenciar Solicitações</h2>

      <ul className="lista-solicitacoes">
        {solicitacoes.map((s) => (
          <li key={s.id} className="card-solicitacao">
            {editandoId === s.id ? (
              <div className="edit-container">
                <div className="header-flex">
                  <h4 style={{ margin: 0 }}>Editando Solicitação #{s.id}</h4>
                </div>

                <p className="status-line">
                  <strong>Situação: </strong>
                  <span
                    className="status-badge"
                    style={{ color: getStatusColor(s.situacao) }}
                  >
                    {s.situacao}
                  </span>
                </p>

                <div className="form-group">
                  <label className="form-label">Descrição:</label>
                  <textarea
                    className="form-input"
                    value={formData.descricao}
                    onChange={(e) =>
                      setFormData({ ...formData, descricao: e.target.value })
                    }
                    rows={3}
                  />
                </div>

                <div className="materiais-section">
                  <h5 style={{ marginTop: 0 }}>Materiais</h5>

                  {formData.materiais.map((mat, idx) => (
                    <div key={idx} className="material-row">
                      <select
                        className="select-material"
                        value={mat.tipoMaterial}
                        onChange={(e) =>
                          handleChangeMaterial(
                            idx,
                            "tipoMaterial",
                            e.target.value,
                          )
                        }
                      >
                        {tiposMaterial.map((t) => (
                          <option key={t} value={t}>
                            {t}
                          </option>
                        ))}
                      </select>

                      <input
                        className="input-peso"
                        type="number"
                        placeholder="Kg"
                        value={mat.peso}
                        min={0}
                        onChange={(e) =>
                          handleChangeMaterial(
                            idx,
                            "peso",
                            Number(e.target.value),
                          )
                        }
                      />
                      <span>kg</span>

                      <button
                        className="btn btn-remove"
                        onClick={() => handleRemoveMaterial(idx)}
                      >
                        X
                      </button>
                    </div>
                  ))}

                  <button className="btn btn-add" onClick={handleAddMaterial}>
                    + Adicionar Material
                  </button>
                </div>

                <div className="btn-group">
                  <button onClick={handleSalvar} className="btn btn-save">
                    Salvar Alterações
                  </button>
                  <button onClick={handleCancelar} className="btn btn-cancel">
                    Cancelar
                  </button>
                </div>
              </div>
            ) : (
              <div>
                <div>
                  <strong>ID: {s.id}</strong>
                  <p
                    className="status-badge"
                    style={{ color: getStatusColor(s.situacao) }}
                  >
                    {s.situacao}
                  </p>
                </div>

                <p style={{ margin: "10px 0" }}>{s.descricao}</p>

                <div className="view-details">
                  <strong>Materiais:</strong>
                  <ul className="materiais-list">
                    {s.materiais?.map((m: any, i: number) => (
                      <li key={i}>
                        {m.tipoMaterial} - {m.quantidadeMaterial}
                      </li>
                    ))}
                  </ul>
                </div>
                <div className="actions-container">
                  {s.situacao !== "APROVADO" && (
                    <div>
                      <button
                        onClick={() => handleEditarClick(s)}
                        className="btn btn-action"
                      >
                        Editar
                      </button>

                      <button
                        onClick={() => handleAprovar(s.id)}
                        className="btn btn-action"
                      >
                        Aprovar
                      </button>
                    </div>
                  )}
                </div>
              </div>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
}
