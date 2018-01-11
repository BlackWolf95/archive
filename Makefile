all:
	ant -buildfile Compiler/build.xml build

test_syntax:
	./scripts/test_syntax.sh

test_parser:
	./scripts/mincaml-test-parser.sh

clean :
	ant -buildfile Compiler/build.xml clean

