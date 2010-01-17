require "rake/clean"

PROJECT = "HelloWorld"

SRC = FileList["src/**/*.scala"]
CLASSES = SRC.map {|f| f.pathmap("%{src,bin}X.class")}
SPEC_SRC = FileList["spec/src/**/*.scala"]
SPEC_CLASSES = SPEC_SRC.map {|f| f.pathmap("%{src,bin}X.class")}
JAR = "pkg/#{PROJECT}.jar"
CLEAN.include %w(bin spec/bin)
CLOBBER.include "pkg"

desc "Build jar file"
task :default => JAR

desc "Run application from jar file"
task :run => JAR do
  exec "scala -cp #{JAR} #{PROJECT}"
end

desc "Run specs"
task :spec => CLASSES + SPEC_CLASSES do
  exec "scala -cp lib/scalatest-1.0.jar:bin:spec/bin org.scalatest.tools.Runner -o -p spec/bin"
end

directory "pkg"
directory "bin"
directory "spec/bin"

file JAR => CLASSES << "pkg" do |t|
  %x(jar cf #{t.name} -C bin .)
end

rule(%r(^bin/.*\.class) => [proc {|f| f.pathmap("%{bin,src}X.scala")}, "bin"]) do |t|
  %x(scalac -d bin #{t.source})
end

rule(%r(^spec/bin/.*\.class) => [proc {|f| f.pathmap("%{bin,src}X.scala")}, "spec/bin"]) do |t|
  %x(scalac -cp lib/scalatest-1.0.jar:bin -d spec/bin #{t.source})
end
