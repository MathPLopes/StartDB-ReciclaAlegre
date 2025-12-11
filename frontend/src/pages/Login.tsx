import { useState } from "react";
import { useContext } from "react";
import { AuthContext } from "../components/auth/AuthContext";

export default function LoginPage() {
  const auth = useContext(AuthContext);
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  if (!auth) {
    return <p>AuthContext não encontrado</p>;
  }

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      await auth.login(email, senha);
    } catch (err: any) {
      setError(err.message || "Erro ao fazer login");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100 p-4">
      <form
        onSubmit={handleSubmit}
        className="bg-white shadow-xl rounded-2xl p-8 w-full max-w-sm space-y-4"
      >
        <h1 className="text-2xl font-bold text-center">Login</h1>

        {error && (
          <p className="text-red-500 text-sm text-center">{error}</p>
        )}

        <div className="flex flex-col gap-1">
          <label className="text-sm font-medium">Email</label>
          <input
            type="email"
            className="border rounded-lg p-2"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>

        <div className="flex flex-col gap-1">
          <label className="text-sm font-medium">Senha</label>
          <input
            type="password"
            className="border rounded-lg p-2"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            required
          />
        </div>

        <button
          type="submit"
          disabled={loading}
          className="w-full bg-green-600 hover:bg-green-700 text-white p-2 rounded-lg mt-4 transition disabled:opacity-50"
        >
          {loading ? "Entrando..." : "Entrar"}
        </button>
      </form>
    </div>
  );
}
