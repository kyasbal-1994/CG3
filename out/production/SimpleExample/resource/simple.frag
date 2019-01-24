//#version 120
//
// simple.frag
//
//uniform mat4 mat;
uniform sampler2D texture0;
uniform float time;
uniform vec3 lightdir;
varying vec3 normal;
varying vec4 color;
varying vec2 texcoord;

void main (void)
{
 //gl_FragColor = max(texture2D(texture0, texcoord * 20.),color.xyzw);
 vec2 originCenter = texcoord * 2.0 -1.0;
 float ts = 1. / 10000.;
 float step = 1000000.*sin(time*ts);
 float diff = cos(time*ts);
 float x = (1. + sin(length(originCenter + vec2(diff,0))*step))*2.;
 float y = (1. + sin(length(originCenter + vec2(0,diff))*step))*2.;
 float z = (1. + sin(length(originCenter + vec2(-diff,0))*step))*2.;
 gl_FragColor = vec4(pow(vec3(x,y,z),vec3(2.2)),1);
//// gl_FragColor = texture2D(texture0, texcoord);
//  gl_FragColor = vec4(0.3, 0.8, 0.2, 1.0);
//  vec2 dfdx = abs(dFdx(texcoord));
//  vec2 dfdy = abs(dFdy(texcoord));
//  float d = max(max(dfdx.x,dfdx.y),max(dfdy.x,dfdy.y));
//  gl_FragColor = vec4(d*20.0,0,0,1);
//gl_FragColor = vec4((0.9*max(dot(normal, normalize(lightdir)),0.0)+0.1)*color.xyz,1.0);
//  gl_FragColor = vec4(1.0,0.8,0.3,1.0);
}

