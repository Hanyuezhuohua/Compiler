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
@INF = common global i32 zeroinitializer, align 4
@n = common global i32 zeroinitializer, align 4
@m = common global i32 zeroinitializer, align 4
@a = common global i32** zeroinitializer, align 4
@str0 = private unnamed_addr constant [2 x i8] c"-1", align 1
@str1 = private unnamed_addr constant [1 x i8] c" ", align 1
@str2 = private unnamed_addr constant [0 x i8] c"", align 1
define void @g_init()

0:
;prev: 
;next: 
	store i32 10000000, i32* @INF, align 4
	ret void 
}
define void @init()

0:
;prev: 
;next: 11 
	%1 = call i32 @_gbl_getInt()
	store i32 %1, i32* @n, align 4
	%2 = call i32 @_gbl_getInt()
	store i32 %2, i32* @m, align 4
	%3 = load i32, i32* @n, align 4
	%4 = mul i32 %3, 4
	%5 = add i32 %4, 4
	%6 = call i8* @malloc(i32 %5)
	%7 = bitcast i8* %6 to i32*
	store i32 %3, i32* %7, align 4
	%8 = getelementptr inbounds i32, i32* %7, i32 1
	%9 = bitcast i32* %8 to i32**
	%22 = mv i32 0
	br label %11
11:
;prev: 0 13 
;next: 13 23 
;iDom: 0
;DomFrontiers: 11 
	%12 = icmp slt i32 %22, %3
	br i1 %12, label %13, label %23
13:
;prev: 11 
;next: 11 
;iDom: 11
;DomFrontiers: 11 
	%14 = getelementptr inbounds i32*, i32** %9, i32 %22
	%15 = load i32, i32* @n, align 4
	%16 = mul i32 %15, 4
	%17 = add i32 %16, 4
	%18 = call i8* @malloc(i32 %17)
	%19 = bitcast i8* %18 to i32*
	store i32 %15, i32* %19, align 4
	%20 = getelementptr inbounds i32, i32* %19, i32 1
	store i32* %20, i32** %14, align 4
	%21 = add i32 %22, 1
	%22 = mv i32 %21
	br label %11
23:
;prev: 11 
;next: 25 
;iDom: 11
	store i32** %9, i32*** @a, align 4
	%47 = mv i32 0
	br label %25
25:
;prev: 23 44 
;next: 28 30 
;iDom: 23
;DomFrontiers: 25 
	%26 = load i32, i32* @n, align 4
	%27 = icmp slt i32 %47, %26
	br i1 %27, label %28, label %30
28:
;prev: 25 
;next: 32 
;iDom: 25
;DomFrontiers: 25 
	%57 = mv i32 0
	br label %32
30:
;prev: 25 
;next: 35 
;iDom: 25
	%60 = mv i32 0
	br label %35
32:
;prev: 28 55 
;next: 38 44 
;iDom: 28
;DomFrontiers: 25 32 
	%33 = load i32, i32* @n, align 4
	%34 = icmp slt i32 %57, %33
	br i1 %34, label %38, label %44
35:
;prev: 30 58 
;next: 48 53 
;iDom: 30
;DomFrontiers: 35 
	%36 = load i32, i32* @n, align 4
	%37 = icmp slt i32 %60, %36
	br i1 %37, label %48, label %53
38:
;prev: 32 
;next: 55 
;iDom: 32
;DomFrontiers: 32 
	%39 = load i32**, i32*** @a, align 4
	%40 = getelementptr inbounds i32*, i32** %39, i32 %47
	%41 = load i32*, i32** %40, align 4
	%42 = getelementptr inbounds i32, i32* %41, i32 %57
	%43 = load i32, i32* @INF, align 4
	store i32 %43, i32* %42, align 4
	br label %55
44:
;prev: 32 
;next: 25 
;iDom: 32
;DomFrontiers: 25 
	%45 = add i32 %47, 1
	%46 = mv i32 %57
	%47 = mv i32 %45
	br label %25
48:
;prev: 35 
;next: 58 
;iDom: 35
;DomFrontiers: 35 
	%49 = load i32**, i32*** @a, align 4
	%50 = getelementptr inbounds i32*, i32** %49, i32 %60
	%51 = load i32*, i32** %50, align 4
	%52 = getelementptr inbounds i32, i32* %51, i32 %60
	store i32 0, i32* %52, align 4
	br label %58
53:
;prev: 35 
;next: 61 
;iDom: 35
	%78 = mv i32 0
	br label %61
55:
;prev: 38 
;next: 32 
;iDom: 38
;DomFrontiers: 32 
	%56 = add i32 %57, 1
	%57 = mv i32 %56
	br label %32
58:
;prev: 48 
;next: 35 
;iDom: 48
;DomFrontiers: 35 
	%59 = add i32 %60, 1
	%60 = mv i32 %59
	br label %35
61:
;prev: 53 73 
;next: 64 72 
;iDom: 53
;DomFrontiers: 61 
	%62 = load i32, i32* @m, align 4
	%63 = icmp slt i32 %78, %62
	br i1 %63, label %64, label %72
64:
;prev: 61 
;next: 73 
;iDom: 61
;DomFrontiers: 61 
	%65 = call i32 @_gbl_getInt()
	%66 = call i32 @_gbl_getInt()
	%67 = call i32 @_gbl_getInt()
	%68 = load i32**, i32*** @a, align 4
	%69 = getelementptr inbounds i32*, i32** %68, i32 %65
	%70 = load i32*, i32** %69, align 4
	%71 = getelementptr inbounds i32, i32* %70, i32 %66
	store i32 %67, i32* %71, align 4
	br label %73
72:
;prev: 61 
;next: 
;iDom: 61
	ret void 
73:
;prev: 64 
;next: 61 
;iDom: 64
;DomFrontiers: 61 
	%74 = add i32 %78, 1
	%75 = mv i32 %65
	%76 = mv i32 %66
	%77 = mv i32 %67
	%78 = mv i32 %74
	br label %61
}
define i32 @main()

0:
;prev: 
;next: 2 
	call void @g_init()
	call void @init()
	%22 = mv i32 0
	br label %2
2:
;prev: 0 19 
;next: 5 8 
;iDom: 0
;DomFrontiers: 2 
	%3 = load i32, i32* @n, align 4
	%4 = icmp slt i32 %22, %3
	br i1 %4, label %5, label %8
5:
;prev: 2 
;next: 11 
;iDom: 2
;DomFrontiers: 2 
	%53 = mv i32 %23
	%54 = mv i32 0
	br label %11
8:
;prev: 2 
;next: 14 
;iDom: 2
	%95 = mv i32 %23
	%96 = mv i32 0
	br label %14
11:
;prev: 5 51 
;next: 17 19 
;iDom: 5
;DomFrontiers: 2 11 
	%12 = load i32, i32* @n, align 4
	%13 = icmp slt i32 %54, %12
	br i1 %13, label %17, label %19
14:
;prev: 8 93 
;next: 24 26 
;iDom: 8
;DomFrontiers: 14 
	%15 = load i32, i32* @n, align 4
	%16 = icmp slt i32 %96, %15
	br i1 %16, label %24, label %26
17:
;prev: 11 
;next: 27 
;iDom: 11
;DomFrontiers: 11 
	%83 = mv i32 0
	br label %27
19:
;prev: 11 
;next: 2 
;iDom: 11
;DomFrontiers: 2 
	%20 = add i32 %22, 1
	%21 = mv i32 %54
	%22 = mv i32 %20
	%23 = mv i32 %53
	br label %2
24:
;prev: 14 
;next: 30 
;iDom: 14
;DomFrontiers: 14 
	%101 = mv i32 0
	br label %30
26:
;prev: 14 
;next: 
;iDom: 14
	ret i32 0
27:
;prev: 17 81 
;next: 33 51 
;iDom: 17
;DomFrontiers: 27 11 
	%28 = load i32, i32* @n, align 4
	%29 = icmp slt i32 %83, %28
	br i1 %29, label %33, label %51
30:
;prev: 24 99 
;next: 55 63 
;iDom: 24
;DomFrontiers: 30 14 
	%31 = load i32, i32* @n, align 4
	%32 = icmp slt i32 %101, %31
	br i1 %32, label %55, label %63
33:
;prev: 27 
;next: 65 81 
;iDom: 27
;DomFrontiers: 27 
	%34 = load i32**, i32*** @a, align 4
	%35 = getelementptr inbounds i32*, i32** %34, i32 %54
	%36 = load i32*, i32** %35, align 4
	%37 = getelementptr inbounds i32, i32* %36, i32 %83
	%38 = load i32**, i32*** @a, align 4
	%39 = getelementptr inbounds i32*, i32** %38, i32 %54
	%40 = load i32*, i32** %39, align 4
	%41 = getelementptr inbounds i32, i32* %40, i32 %22
	%42 = load i32**, i32*** @a, align 4
	%43 = getelementptr inbounds i32*, i32** %42, i32 %22
	%44 = load i32*, i32** %43, align 4
	%45 = getelementptr inbounds i32, i32* %44, i32 %83
	%46 = load i32, i32* %41, align 4
	%47 = load i32, i32* %45, align 4
	%48 = add i32 %46, %47
	%49 = load i32, i32* %37, align 4
	%50 = icmp sgt i32 %49, %48
	br i1 %50, label %65, label %81
51:
;prev: 27 
;next: 11 
;iDom: 27
;DomFrontiers: 11 
	%52 = add i32 %54, 1
	%53 = mv i32 %83
	%54 = mv i32 %52
	br label %11
55:
;prev: 30 
;next: 84 86 
;iDom: 30
;DomFrontiers: 30 
	%56 = load i32**, i32*** @a, align 4
	%57 = getelementptr inbounds i32*, i32** %56, i32 %96
	%58 = load i32*, i32** %57, align 4
	%59 = getelementptr inbounds i32, i32* %58, i32 %101
	%60 = load i32, i32* %59, align 4
	%61 = load i32, i32* @INF, align 4
	%62 = icmp eq i32 %60, %61
	br i1 %62, label %84, label %86
63:
;prev: 30 
;next: 93 
;iDom: 30
;DomFrontiers: 14 
	%64 = getelementptr inbounds [0 x i8], [0 x i8]* @str2, i32 0, i32 0
	call void @_gbl_println(i8* %64)
	br label %93
65:
;prev: 33 
;next: 81 
;iDom: 33
;DomFrontiers: 81 
	%66 = load i32**, i32*** @a, align 4
	%67 = getelementptr inbounds i32*, i32** %66, i32 %54
	%68 = load i32*, i32** %67, align 4
	%69 = getelementptr inbounds i32, i32* %68, i32 %83
	%70 = load i32**, i32*** @a, align 4
	%71 = getelementptr inbounds i32*, i32** %70, i32 %54
	%72 = load i32*, i32** %71, align 4
	%73 = getelementptr inbounds i32, i32* %72, i32 %22
	%74 = load i32**, i32*** @a, align 4
	%75 = getelementptr inbounds i32*, i32** %74, i32 %22
	%76 = load i32*, i32** %75, align 4
	%77 = getelementptr inbounds i32, i32* %76, i32 %83
	%78 = load i32, i32* %73, align 4
	%79 = load i32, i32* %77, align 4
	%80 = add i32 %78, %79
	store i32 %80, i32* %69, align 4
	br label %81
81:
;prev: 33 65 
;next: 27 
;iDom: 33
;DomFrontiers: 27 
	%82 = add i32 %83, 1
	%83 = mv i32 %82
	br label %27
84:
;prev: 55 
;next: 97 
;iDom: 55
;DomFrontiers: 97 
	%85 = getelementptr inbounds [2 x i8], [2 x i8]* @str0, i32 0, i32 0
	call void @_gbl_print(i8* %85)
	br label %97
86:
;prev: 55 
;next: 97 
;iDom: 55
;DomFrontiers: 97 
	%87 = load i32**, i32*** @a, align 4
	%88 = getelementptr inbounds i32*, i32** %87, i32 %96
	%89 = load i32*, i32** %88, align 4
	%90 = getelementptr inbounds i32, i32* %89, i32 %101
	%91 = load i32, i32* %90, align 4
	%92 = call i8* @_gbl_toString(i32 %91)
	call void @_gbl_print(i8* %92)
	br label %97
93:
;prev: 63 
;next: 14 
;iDom: 63
;DomFrontiers: 14 
	%94 = add i32 %96, 1
	%95 = mv i32 %101
	%96 = mv i32 %94
	br label %14
97:
;prev: 84 86 
;next: 99 
;iDom: 55
;DomFrontiers: 30 
	%98 = getelementptr inbounds [1 x i8], [1 x i8]* @str1, i32 0, i32 0
	call void @_gbl_print(i8* %98)
	br label %99
99:
;prev: 97 
;next: 30 
;iDom: 97
;DomFrontiers: 30 
	%100 = add i32 %101, 1
	%101 = mv i32 %100
	br label %30
}
