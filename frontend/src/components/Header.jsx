import React from 'react'
import { Link } from 'react-router-dom'


export default function Header(){
return (
<header className="site-header">
<div style={{maxWidth:1100, margin:'0 auto', display:'flex', alignItems:'center', justifyContent:'space-between'}}>
<h2 style={{margin:0}}>ReciclaAlegre</h2>
<nav>
<Link to="/">Home</Link>
<Link to="/collection">Coleta</Link>
<Link to="/about">Sobre</Link>
<Link to="/admin">Admin</Link>
<Link to="/login">Login</Link>
</nav>
</div>
</header>
)
}