import React, {useState} from 'react'
import { fetchJSON } from '../api'


export default function Cadastro(){
const [form, setForm] = useState({name:'', email:'', senha:''})


async function handle(e){
e.preventDefault()
    try{
    await fetchJSON('/users', { method:'POST', body: JSON.stringify(form) })
    alert('Cadastrado com sucesso!')
    }catch(err){
    alert('Erro: '+err.message)
}
}


return (
    <div className="container">
    <h1>Cadastro</h1>

    <form onSubmit={handle}>

        <input placeholder="Nome" value={form.name} onChange={e=>setForm({...form,name:e.target.value})} />
        <input placeholder="Email" value={form.email} onChange={e=>setForm({...form,email:e.target.value})} />
        <input placeholder="Senha" type="senha" value={form.senha} onChange={e=>setForm({...form,senha:e.target.value})} />
        <button className="btn btn-primary" type="submit">Criar conta</button>
   </form>
   
    </div>
)
}