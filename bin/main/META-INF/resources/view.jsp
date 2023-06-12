<%@ include file="/init.jsp" %>

<portlet:defineObjects />
<portlet:actionURL name="registerUser" var="registerUser"> </portlet:actionURL>

<div class="container items-align-center justify-content-center mt-5">
	<h2>Cadastro de usuário</h2>

	<form action="${registerUser}" method="post">
		<div class="form-row">
			<div class="col-md-6 form-group">
				<label for="firstName">Primeiro nome</label>

				<input
					class="form-control"
					id="firstName"
					name="<portlet:namespace />firstName"
					placeholder="Fulano"
					type="text"
					value="Fulano"
				/>
			</div>

			<div class="col-md-6 form-group">
				<label for="lastName">Último nome</label>

				<input
					class="form-control"
					id="lastName"
					name="<portlet:namespace />lastName"
					placeholder="Sicrano"
					type="text"
					value="Sicrano"
				/>
			</div>
		</div>

		<div class="form-group">
			<label for="cpf">CPF</label>

			<input
				class="form-control"
				id="cpf"
				maxlength="14"
				minlength="11"
				name="<portlet:namespace />cpf"
				oninput="this.value = formatCPF(this.value)"
				placeholder="999.999.999-99"
				type="text"
				value="99999999999"
			/>
		</div>

		<div class="form-group">
			<label for="email">Nome de usuário</label>

			<input
				class="form-control"
				id="screenName"
				name="<portlet:namespace />screenName"
				placeholder="Fulano da Silva"
				type="text"
				value="fulano_da_silva"
			/>
		</div>

		<div class="form-group">
			<label for="email">Email</label>

			<input
				class="form-control"
				id="email"
				name="<portlet:namespace />emailAddress"
				placeholder="sicrano@yahoo.com"
				type="email"
				value="sicrano@yahoo.com"
			/>
		</div>

		<div class="form-group">
			<div class="form-check">
				<input
					class="form-check-input"
					name="<portlet:namespace />isDeveloper"
					type="checkbox"
					id="isDeveloper"
					checked
				/>

				<label class="form-check-label" for="isDeveloper">
					É desenvolvedor?
				</label>
			</div>
		</div>

		<button
			class="btn btn-primary"
			name="<portlet:namespace />submitRegister"
			type="submit"
		>
			Cadastrar
		</button>
	</form>
</div>

<script>
function formatCPF(cpf) {
	cpf = cpf.replace(/[^\d]/g, "");
	return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
}
</script>