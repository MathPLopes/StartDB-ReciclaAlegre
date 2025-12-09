import React, {useEffect, useState} from 'react'
import { fetchJSON } from '../api'


export default function Perfil(){
const [perfil, setPerfil] = useState(null)


useEffect(()=>{
let mounted = true
fetchJSON('/me').then(data=>{ if(mounted) setPerfil(data) }).catch(()=>{})
return ()=> mounted=false
},[])


if(!profile) return <div className="container">Carregando...</div>

return (
    <div className="container">
        <h1>Perfil</h1>
        <p><strong>Nome:</strong> {nome.perfil}</p>
        <p><strong>Email:</strong> {email.perfil}</p>
    </div>
)
}