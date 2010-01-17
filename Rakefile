require "rake/clean"

PROJECT = "HelloWorld"

SRC = FileList["src/**/*.scala"]
CLASSES = SRC.map {|f| f.pathmap("%{^src,bin}X.class")}
JAR = "pkg/#{PROJECT}.jar"
CLEAN.include "bin"
CLOBBER.include "pkg"

desc "Build jar file"
task :default => JAR

desc "Run application from jar file"
task :run do
  exec "scala -cp #{JAR} #{PROJECT}"
end

directory "pkg"
directory "bin"

file JAR => CLASSES << "pkg" do |t|
  %x(jar cf #{t.name} -C bin .)
end

rule ".class" => [proc {|f| f.pathmap("%{^bin,src}X.scala")}, "bin"] do |t|
  %x(scalac -d bin #{t.source})
end
