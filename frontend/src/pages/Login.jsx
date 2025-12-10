import React, {useState} from 'react'
import { useNavigate } from 'react-router-dom'
import { fetchJSON } from '../api'


export default function Login(){
const [email, setEmail] = useState('')
const [senha, setSenha] = useState('')
const nav = useNavigate()


async function handle(e){
e.preventDefault()
try{

await fetchJSON('/auth/login', { method:'POST', body: JSON.stringify({email,senha}) })

nav('/')
}catch(err)
{alert('Erro: '+err.message)}
}


return (
<div className="container">
    <h1>Login</h1>
    <form onSubmit={handle}>

        <div>

        <label>Email</label>
        <input value={email} onChange={e=>setEmail(e.target.value)} />
        </div>

        <div>
         <label>Senha</label>
         <input type="senha" value={senha} onChange={e=>setSenha(e.target.value)} />
        </div>

        <button className="btn btn-primary" type="submit">Entrar</button>
    </form>
</div>
)
}