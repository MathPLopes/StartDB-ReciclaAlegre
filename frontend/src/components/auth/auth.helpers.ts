import type { UsuarioRequestDTO, UsuarioResponseDTO, LoginDTO } from "./AuthContext.types";

const API_URL = "http://localhost:8080";

export async function registrarUsuario(
    data: UsuarioRequestDTO
):Promise<UsuarioResponseDTO> {
    const response = await fetch(`${API_URL}/api/usuarios`,{
        method: "POST",
        headers: {
            "Content-Type":"application/json"
        },
        body:JSON.stringify(data)
    });

    if(!response.ok){
        const text = await response.text();
        throw new Error(`Error ao registrar usuario ${text}`);
    }

    return await response.json();
}

export async function loginUsuario(data: LoginDTO): Promise<string> {
    const response = await fetch(`${API_URL}/api/auth`, {
        method: "POST",
        headers:{
            "content-type":"application/json"
        },
        body: JSON.stringify(data)
    })
    if(!response.ok){
        throw new Error(await response.text())
    }
    return await response.text();
}

export async function fetchUser(token: string): Promise<UsuarioResponseDTO> {
  const response = await fetch(`${API_URL}/api/usuarios/perfil`, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
  if (!response.ok) {
    throw new Error(await response.text());
  }
  return await response.json();
}