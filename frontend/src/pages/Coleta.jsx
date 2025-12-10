import React, {useState} from 'react'
import { fetchJSON } from '../api'


export default function Coleta(){
const [address, setAddress] = useState('')
const [data, setData] = useState('')


async function handle(e){

e.preventDefault()

try{
    await fetchJSON('/collections', { method:'POST', body: JSON.stringify({address,data}) })
    alert('Agendamento realizado')

}   catch(err){

alert('Erro: '+err.message)
    }
}


return (
 <div className="container">
    <h1>Agendar Coleta</h1>
    <form onSubmit={handle}>
            <input placeholder="Endereço" value={address} onChange={e=>setAddress(e.target.value)} />
            <input type="date" value={date} onChange={e=>setDate(e.target.value)} />
            <button className="btn btn-primary" type="submit">Agendar</button>
    </form>
</div>
)
}