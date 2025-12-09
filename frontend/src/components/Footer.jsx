import React from 'react'


export default function Footer(){
return (
<footer className="site-footer">
<div style={{maxWidth:1100, margin:'0 auto', textAlign:'center'}}>
<small>© {new Date().getFullYear()} ReciclaAlegre</small>
</div>
</footer>
)
}