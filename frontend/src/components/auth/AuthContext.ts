import { createContext } from "react";
import type { UsuarioResponseDTO, UsuarioRequestDTO } from "./AuthContext.types";

export interface AuthContextType {
  user: UsuarioResponseDTO | null;
  token: string | null;
  loading: boolean;
  isAuthenticated: boolean;

  register: (data: UsuarioRequestDTO) => Promise<void>;
  login: (email: string, senha: string) => Promise<void>;
  logout: () => Promise<void>;
}

export const AuthContext = createContext<AuthContextType | undefined>(undefined);
