declare i32 @_str_length(i8* %0)
declare i8* @_str_substring(i8* %0, i32 %1, i32 %2)
declare i32 @_str_parseInt(i8* %0)
declare i32 @_str_ord(i8* %0, i32 %1)
declare i8* @_str_concat(i8* %0, i8* %1)
declare i1 @_str_eq(i8* %0, i8* %1)
declare i1 @_str_ne(i8* %0, i8* %1)
declare i1 @_str_lt(i8* %0, i8* %1)
declare i1 @_str_gt(i8* %0, i8* %1)
declare i1 @_str_le(i8* %0, i8* %1)
declare i1 @_str_ge(i8* %0, i8* %1)
declare void @_gbl_print(i8* %0)
declare void @_gbl_println(i8* %0)
declare void @_gbl_printInt(i32 %0)
declare void @_gbl_printlnInt(i32 %0)
declare i8* @_gbl_getString()
declare i32 @_gbl_getInt()
declare i8* @_gbl_toString(i32 %0)
declare i8* @malloc(i32 %0)
@a = common global i32* zeroinitializer, align 4
@str0 = private unnamed_addr constant [0 x i8] c"", align 1
define void @g_init()

0:
;prev: 
;next: 
	%1 = call i8* @malloc(i32 20)
	%2 = bitcast i8* %1 to i32*
	store i32 4, i32* %2, align 4
	%3 = getelementptr inbounds i32, i32* %2, i32 1
	store i32* %3, i32** @a, align 4
	ret void 
}
define i32 @main()

0:
;prev: 
;next: 18 
	call void @g_init()
	%1 = call i8* @malloc(i32 20)
	%2 = bitcast i8* %1 to i32*
	store i32 4, i32* %2, align 4
	%3 = getelementptr inbounds i32, i32* %2, i32 1
	%4 = bitcast i32* %3 to i32**
	%5 = getelementptr inbounds i32*, i32** %4, i32 0
	%6 = load i32*, i32** @a, align 4
	store i32* %6, i32** %5, align 4
	%7 = getelementptr inbounds i32*, i32** %4, i32 1
	%8 = load i32*, i32** @a, align 4
	store i32* %8, i32** %7, align 4
	%9 = getelementptr inbounds i32*, i32** %4, i32 2
	%10 = load i32*, i32** @a, align 4
	store i32* %10, i32** %9, align 4
	%11 = getelementptr inbounds i32*, i32** %4, i32 3
	%12 = load i32*, i32** @a, align 4
	store i32* %12, i32** %11, align 4
	%13 = bitcast i32** %4 to i32*
	%14 = getelementptr inbounds i32, i32* %13, i32 -1
	%15 = load i32, i32* %14, align 4
	%16 = call i8* @_gbl_toString(i32 %15)
	call void @_gbl_println(i8* %16)
	%33 = mv i32 0
	br label %18
18:
;prev: 0 31 
;next: 24 29 
;iDom: 0
;DomFrontiers: 18 
	%19 = getelementptr inbounds i32*, i32** %4, i32 0
	%20 = load i32*, i32** %19, align 4
	%21 = getelementptr inbounds i32, i32* %20, i32 -1
	%22 = load i32, i32* %21, align 4
	%23 = icmp slt i32 %33, %22
	br i1 %23, label %24, label %29
24:
;prev: 18 
;next: 31 
;iDom: 18
;DomFrontiers: 18 
	%25 = getelementptr inbounds i32*, i32** %4, i32 0
	%26 = load i32*, i32** %25, align 4
	%27 = getelementptr inbounds i32, i32* %26, i32 %33
	%28 = call i32 @_gbl_getInt()
	store i32 %28, i32* %27, align 4
	br label %31
29:
;prev: 18 
;next: 34 
;iDom: 18
	%51 = mv i32 0
	br label %34
31:
;prev: 24 
;next: 18 
;iDom: 24
;DomFrontiers: 18 
	%32 = add i32 %33, 1
	%33 = mv i32 %32
	br label %18
34:
;prev: 29 49 
;next: 40 46 
;iDom: 29
;DomFrontiers: 34 
	%35 = getelementptr inbounds i32*, i32** %4, i32 1
	%36 = load i32*, i32** %35, align 4
	%37 = getelementptr inbounds i32, i32* %36, i32 -1
	%38 = load i32, i32* %37, align 4
	%39 = icmp slt i32 %51, %38
	br i1 %39, label %40, label %46
40:
;prev: 34 
;next: 49 
;iDom: 34
;DomFrontiers: 34 
	%41 = getelementptr inbounds i32*, i32** %4, i32 1
	%42 = load i32*, i32** %41, align 4
	%43 = getelementptr inbounds i32, i32* %42, i32 %51
	%44 = load i32, i32* %43, align 4
	%45 = call i8* @_gbl_toString(i32 %44)
	call void @_gbl_print(i8* %45)
	br label %49
46:
;prev: 34 
;next: 52 
;iDom: 34
	%47 = getelementptr inbounds [0 x i8], [0 x i8]* @str0, i32 0, i32 0
	call void @_gbl_println(i8* %47)
	%66 = mv i32 0
	br label %52
49:
;prev: 40 
;next: 34 
;iDom: 40
;DomFrontiers: 34 
	%50 = add i32 %51, 1
	%51 = mv i32 %50
	br label %34
52:
;prev: 46 64 
;next: 58 62 
;iDom: 46
;DomFrontiers: 52 
	%53 = getelementptr inbounds i32*, i32** %4, i32 2
	%54 = load i32*, i32** %53, align 4
	%55 = getelementptr inbounds i32, i32* %54, i32 -1
	%56 = load i32, i32* %55, align 4
	%57 = icmp slt i32 %66, %56
	br i1 %57, label %58, label %62
58:
;prev: 52 
;next: 64 
;iDom: 52
;DomFrontiers: 52 
	%59 = getelementptr inbounds i32*, i32** %4, i32 2
	%60 = load i32*, i32** %59, align 4
	%61 = getelementptr inbounds i32, i32* %60, i32 %66
	store i32 0, i32* %61, align 4
	br label %64
62:
;prev: 52 
;next: 67 
;iDom: 52
	%82 = mv i32 0
	br label %67
64:
;prev: 58 
;next: 52 
;iDom: 58
;DomFrontiers: 52 
	%65 = add i32 %66, 1
	%66 = mv i32 %65
	br label %52
67:
;prev: 62 80 
;next: 73 79 
;iDom: 62
;DomFrontiers: 67 
	%68 = getelementptr inbounds i32*, i32** %4, i32 3
	%69 = load i32*, i32** %68, align 4
	%70 = getelementptr inbounds i32, i32* %69, i32 -1
	%71 = load i32, i32* %70, align 4
	%72 = icmp slt i32 %82, %71
	br i1 %72, label %73, label %79
73:
;prev: 67 
;next: 80 
;iDom: 67
;DomFrontiers: 67 
	%74 = getelementptr inbounds i32*, i32** %4, i32 3
	%75 = load i32*, i32** %74, align 4
	%76 = getelementptr inbounds i32, i32* %75, i32 %82
	%77 = load i32, i32* %76, align 4
	%78 = call i8* @_gbl_toString(i32 %77)
	call void @_gbl_print(i8* %78)
	br label %80
79:
;prev: 67 
;next: 
;iDom: 67
	ret i32 0
80:
;prev: 73 
;next: 67 
;iDom: 73
;DomFrontiers: 67 
	%81 = add i32 %82, 1
	%82 = mv i32 %81
	br label %67
}
